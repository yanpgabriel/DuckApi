package dev.yanpgabriel.duck.modules.bot.player;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import dev.yanpgabriel.duck.modules.bot.utils.AdvancedAudioTrack;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.List;
import java.util.Objects;

public class AudioLoadResult implements AudioLoadResultHandler {

    private final MessageReceivedEvent event;
    private final GuildMusicManager musicManager;
    private final Member executor;
    private final String pesquisaOriginal;

    public AudioLoadResult(MessageReceivedEvent event, GuildMusicManager musicManager, Member executor, String pesquisaOriginal) {
        this.event = event;
        this.musicManager = musicManager;
        this.executor = executor;
        this.pesquisaOriginal = pesquisaOriginal;
        this.musicManager.scheduler.updateMessageIfIsNull(event.getChannel());
    }

    @Override
    public void trackLoaded(AudioTrack track) {
        musicManager.scheduler.deleteInfoMessage();
        AdvancedAudioTrack advancedAudioTrack = new AdvancedAudioTrack(track);
        advancedAudioTrack.setUsername(Objects.nonNull(executor.getNickname()) ? executor.getNickname() : executor.getUser().getName());
        advancedAudioTrack.setAvatarUrl(executor.getUser().getAvatarUrl());
        advancedAudioTrack.setChannelName(event.getGuild().getSelfMember().getVoiceState().getChannel().getName());
        musicManager.scheduler.queue(advancedAudioTrack);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        AudioTrack firstTrack = playlist.getSelectedTrack();

        if (firstTrack == null) {
            firstTrack = playlist.getTracks().get(0);
        }

        event.getMessage().delete().queue();
        
        AdvancedAudioTrack advancedAudioTrack = new AdvancedAudioTrack(firstTrack);
        advancedAudioTrack.setUsername(Objects.nonNull(executor.getNickname()) ? executor.getNickname() : executor.getUser().getName());
        advancedAudioTrack.setAvatarUrl(executor.getUser().getAvatarUrl());
        advancedAudioTrack.setChannelName(event.getGuild().getSelfMember().getVoiceState().getChannel().getName());
        
        if (playlist.isSearchResult()) {
            advancedAudioTrack.setSearch(true);
            musicManager.scheduler.queue(advancedAudioTrack);
            return;
        }

        final List<AudioTrack> tracks = playlist.getTracks();
        
        advancedAudioTrack.setPlaylist(true);
        advancedAudioTrack.setPlaylistName(playlist.getName());
        advancedAudioTrack.setPlaylistSize(tracks.size());
        musicManager.scheduler.queue(advancedAudioTrack);

        for (final AudioTrack track : tracks) {
            if (firstTrack.getInfo().title.equalsIgnoreCase(track.getInfo().title)) {
                continue;
            }
            AdvancedAudioTrack advancedAudioTrackPlaylist = new AdvancedAudioTrack(track.makeClone());
            advancedAudioTrackPlaylist.setUsername(Objects.nonNull(executor.getNickname()) ? executor.getNickname() : executor.getUser().getName());
            advancedAudioTrackPlaylist.setAvatarUrl(executor.getUser().getAvatarUrl());
            advancedAudioTrackPlaylist.setChannelName(event.getGuild().getSelfMember().getVoiceState().getChannel().getName());
            advancedAudioTrackPlaylist.setPlaylist(true);
            advancedAudioTrackPlaylist.setPlaylistName(playlist.getName());
            musicManager.scheduler.queue(advancedAudioTrackPlaylist);
        }
    }

    @Override
    public void noMatches() {
        event.getChannel().sendMessage("Não encontramos nada ao pesquisa: " + pesquisaOriginal).queue();
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        event.getChannel().sendMessage("Não foi possivel tocar: " + exception.getMessage()).queue();
    }

}
