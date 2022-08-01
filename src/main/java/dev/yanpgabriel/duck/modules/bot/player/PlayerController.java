package dev.yanpgabriel.duck.modules.bot.player;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.http.HttpAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.local.LocalAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.twitch.TwitchStreamAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.vimeo.VimeoAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PlayerController {

    public static final AudioPlayerManager playerManager;
    public static final Map<Long, GuildMusicManager> musicManagers;

    static {
        musicManagers = new HashMap<>();

        playerManager = new DefaultAudioPlayerManager();
        playerManager.registerSourceManager(new YoutubeAudioSourceManager());
        playerManager.registerSourceManager(new VimeoAudioSourceManager());
        playerManager.registerSourceManager(new TwitchStreamAudioSourceManager());
        playerManager.registerSourceManager(new HttpAudioSourceManager());
        playerManager.registerSourceManager(new LocalAudioSourceManager());
        AudioSourceManagers.registerRemoteSources(playerManager);
        AudioSourceManagers.registerLocalSource(playerManager);
    }
    
    public static AudioPlayerManager getInstance() {
        return playerManager;
    }

    public static synchronized GuildMusicManager getGuildAudioPlayer(Guild guild) {
        return getGuildAudioPlayer(guild, null);
    }
    
    public static synchronized GuildMusicManager getGuildAudioPlayer(Guild guild, MessageChannel messageChannel) {
        var guildId = Long.parseLong(guild.getId());
        var musicManager = musicManagers.get(guildId);

        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager, messageChannel);
            musicManagers.put(guildId, musicManager);
        }

        guild.getAudioManager().setSendingHandler(musicManager.getSendHandler());

        return musicManager;
    }

    public static synchronized GuildMusicManager getGuildAudioPlayerById(String idServidor) {
        return getGuildAudioPlayerById(idServidor, null);
    }
    
    public static synchronized GuildMusicManager getGuildAudioPlayerById(String idServidor, MessageChannel messageChannel) {
        var guildId = Long.parseLong(idServidor);
        var musicManager = musicManagers.get(guildId);
        if (musicManager == null) {
            musicManager = new GuildMusicManager(playerManager, messageChannel);
            musicManagers.put(guildId, musicManager);
        }
        return musicManager;
    }
    
    public static synchronized Map<Long, GuildMusicManager> getAllGuildAudioPlayers() {
        return musicManagers;
    }

    public static String formatTime(long timeInMillis) {
        final var hours = timeInMillis / TimeUnit.HOURS.toMillis(1);
        final var minutes = timeInMillis / TimeUnit.MINUTES.toMillis(1);
        final var seconds = timeInMillis % TimeUnit.MINUTES.toMillis(1) / TimeUnit.SECONDS.toMillis(1);

        var res = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        if (res.startsWith("00:")) {
            res = res.substring(3);
        }
        return res;
    }

    public static boolean isUrl(String url) {
        try {
            new URI(url);
            return true;
        } catch (URISyntaxException e) {
            return false;
        }
    }
    
    public static void shutdown() {
        musicManagers.forEach((guildId, guildMusicManager) -> guildMusicManager.scheduler.deleteInfoMessage());
    }
}
