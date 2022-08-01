package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class NowCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getTextChannel();

        BotUtils.checkIamVoiceChannel(event);
        BotUtils.checkInVoiceChannel(event);
        BotUtils.checkSameVoiceChannel(event);
        
        var musicManager = PlayerController.getGuildAudioPlayer(event.getGuild());
        var audioPlayer = musicManager.player;
        var track = audioPlayer.getPlayingTrack();

        if (track == null) {
            channel.sendMessage("`Não há nenhuma faixa sendo reproduzida no momento.`").queue();
            return;
        }

        var advancedAudioTrackNow = musicManager.scheduler.advancedAudioTrackNow;
        advancedAudioTrackNow.setAudioTrack(track);
        musicManager.scheduler.updateMessageIfIsNull(channel);
        musicManager.scheduler.dispatchPlayMessage(advancedAudioTrackNow);
        event.getMessage().delete().queue();
    }

}
