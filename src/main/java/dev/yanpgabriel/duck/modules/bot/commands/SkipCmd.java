package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class SkipCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getChannel();

        BotUtils.checkIamVoiceChannel(event);
        BotUtils.checkInVoiceChannel(event);
        BotUtils.checkSameVoiceChannel(event);

        var musicManager = PlayerController.getGuildAudioPlayer(event.getGuild());
        var audioPlayer = musicManager.player;

        if (audioPlayer.getPlayingTrack() == null) {
            event.getMessage().addReaction(BotReaction.DEDO_DO_MEIO.toString()).queue();
            channel.sendMessage("`Não há nenhuma faixa sendo reproduzida no momento.`").queue();
            return;
        }

        event.getMessage().delete().queue();
        musicManager.scheduler.nextTrack();
    }

}
