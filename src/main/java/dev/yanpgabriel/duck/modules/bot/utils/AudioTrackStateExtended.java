package dev.yanpgabriel.duck.modules.bot.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrackState;

public enum AudioTrackStateExtended {
    INACTIVE,
    LOADING,
    PLAYING,
    SEEKING,
    STOPPING,
    FINISHED,
    PAUSED;

    private AudioTrackStateExtended() {
    }
    
    public static AudioTrackStateExtended valueOf(AudioTrackState audioTrackState) {
        return AudioTrackStateExtended.values()[audioTrackState.ordinal()];
    }
}
