package dev.yanpgabriel.duck.modules.bot.commands;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.BotSocket;
import dev.yanpgabriel.duck.modules.bot.player.AudioLoadResult;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.AdvancedAudioTrack;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class PlayCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var msg = event.getMessage();
        var channel = event.getChannel();
        if (args.length < 2) {
            msg.addReaction(BotReaction.DEDO_DO_MEIO.toString()).queue();
            channel.sendMessage("Tem que colocar alguma coisa depois do play pô...").queue();
            return;
        }
        BotUtils.joinVoiceChat(event,
            (unused) -> {
                try {
                    Thread.sleep(500);
                    BotUtils.checkInVoiceChannel(event);
                    BotUtils.checkSameVoiceChannel(event);

                    var pesquisaOriginal = args[1];
                    var pesquisa = args[1];

                    var isUrl = PlayerController.isUrl(pesquisa) && pesquisa.contains("http");

                    if (!isUrl && !pesquisa.contains("ytsearch:")) {
                        pesquisa = "ytsearch: " + pesquisa;
                    }
                    var musicManager = PlayerController.getGuildAudioPlayer(event.getGuild(), channel);

                    PlayerController.playerManager.loadItemOrdered(musicManager, pesquisa, new AudioLoadResult(event, musicManager, msg.getMember(), pesquisaOriginal));
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Deu erro quando dormiu a thread bro.");
                }
            });
    }

    @Override
    public void runSocket(BotSocket socket, Guild guild, String... args) {
        if (args.length < 2) {
            socket.broadcast("Tem que colocar alguma coisa depois do play pô...");
            return;
        }
        var selfVoiceState = guild.getSelfMember().getVoiceState();
        if (!selfVoiceState.inVoiceChannel()) {
            socket.broadcast("Eu preciso estar em um canal de voz para que isso funcione.");
            return;
        }
        var pesquisaOriginal = args[1];
        var pesquisa = args[1];
        if (!PlayerController.isUrl(pesquisa)) {
            pesquisa = "ytsearch: " + pesquisa;
        }
        var musicManager = PlayerController.getGuildAudioPlayer(guild);
        PlayerController.playerManager.loadItemOrdered(musicManager, pesquisa, new AudioLoadResultHandler() {
            @Override
            public void trackLoaded(AudioTrack track) {
                socket.broadcast("Adicionando na queue " + track.getInfo().title);
                musicManager.scheduler.queue(new AdvancedAudioTrack(track));
            }
            @Override
            public void playlistLoaded(AudioPlaylist playlist) {
                var firstTrack = playlist.getSelectedTrack();

                if (firstTrack == null) {
                    firstTrack = playlist.getTracks().get(0);
                }
                musicManager.scheduler.queue(new AdvancedAudioTrack(firstTrack));
                if (playlist.isSearchResult()) {
                    socket.broadcast("Adicionando na queue " + firstTrack.getInfo().title);
                    return;
                }
                socket.broadcast("Adicionando na queue " + firstTrack.getInfo().title + " (primeira faixa da playlist " + playlist.getName() + ")");
                var tracks = playlist.getTracks();
                for (var track : tracks) {
                    if (firstTrack.getInfo().title.equalsIgnoreCase(track.getInfo().title)) {
                        continue;
                    }
                    musicManager.scheduler.queue(new AdvancedAudioTrack(track.makeClone()));
                }
                socket.broadcast("Adicionando na queue: `" + 
                        tracks.size() + 
                        "` faixas da playlist `" + 
                        playlist.getName() + 
                        '`');
            }
            @Override
            public void noMatches() {
                socket.broadcast("Não encontramos nada ao pesquisa: " + pesquisaOriginal);
            }
            @Override
            public void loadFailed(FriendlyException exception) {
                socket.broadcast("Não foi possivel tocar: " + exception.getMessage());
            }
        });
    }
}
