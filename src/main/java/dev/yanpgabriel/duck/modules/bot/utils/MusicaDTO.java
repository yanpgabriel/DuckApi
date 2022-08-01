package dev.yanpgabriel.duck.modules.bot.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;

public class MusicaDTO {
    
    private AudioTrackInfo info;
    private AudioTrackStateExtended state;
    private String minutagem;
    private String duracao;
    private Object outros;

    public MusicaDTO() {
    }

    public MusicaDTO(AudioTrack track) {
        this(track, false);
    }

    public MusicaDTO(AudioTrack track, Boolean paused) {
        this.info = track.getInfo();
        this.state = paused ? AudioTrackStateExtended.PAUSED : AudioTrackStateExtended.valueOf(track.getState());
        this.minutagem = PlayerController.formatTime(track.getPosition());
        this.duracao = PlayerController.formatTime(track.getDuration());
        this.outros = track.getUserData();
    }

    public AudioTrackInfo getInfo() {
        return info;
    }

    public void setInfo(AudioTrackInfo info) {
        this.info = info;
    }

    public AudioTrackStateExtended getState() {
        return state;
    }

    public void setState(AudioTrackStateExtended state) {
        this.state = state;
    }

    public String getMinutagem() {
        return minutagem;
    }

    public void setMinutagem(String minutagem) {
        this.minutagem = minutagem;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public Object getOutros() {
        return outros;
    }

    public void setOutros(Object outros) {
        this.outros = outros;
    }
}
