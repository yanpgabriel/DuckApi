package dev.yanpgabriel.duck.modules.bot;

public enum BotReaction {
    
    DEDO_DO_MEIO("\uD83E\uDD14"),
    TOPPEN("\uD83D\uDC4C"),
    PLAY_PAUSE("\u23EF\uFE0F"),
    NEXT("\u23ED\uFE0F"),
    STOP("\u23F9\uFE0F"),
    ;
    
    private String emoji;
    
    BotReaction(String emoji) {
        this.emoji = emoji;
    }

    @Override
    public String toString() {
        return emoji;
    }

}
