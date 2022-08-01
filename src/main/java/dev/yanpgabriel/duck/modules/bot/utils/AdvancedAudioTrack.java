package dev.yanpgabriel.duck.modules.bot.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class AdvancedAudioTrack {
    
    private String username;
    private String avatarUrl;
    private String channelName;
    private AudioTrack audioTrack;
    private boolean search = false;
    private int playlistSize = 0;
    private String playlistName = "";
    private boolean playlist = false;

    public AdvancedAudioTrack(AudioTrack audioTrack) {
        this.audioTrack = audioTrack;
    }


    public AdvancedAudioTrack(AudioTrack audioTrack, String username) {
        this(audioTrack);
        this.username = username;
    }

    public AudioTrack getAudioTrack() {
        return audioTrack;
    }
    
    public void setAudioTrack(AudioTrack audioTrack) {
        this.audioTrack = audioTrack;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public boolean isPlaylist() {
        return playlist;
    }
    
    public void setPlaylist(boolean playlist) {
        this.playlist = playlist;
    }

    public int getPlaylistSize() {
        return playlistSize;
    }

    public void setPlaylistSize(int playlistSize) {
        this.playlistSize = playlistSize;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }
}
