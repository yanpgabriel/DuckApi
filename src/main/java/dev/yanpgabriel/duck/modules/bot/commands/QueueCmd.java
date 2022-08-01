package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;

@BotCommand
public class QueueCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getTextChannel();
        var musicManager = PlayerController.getGuildAudioPlayer(event.getGuild());
        var player = musicManager.player;
        var playingTrack = player.getPlayingTrack();
        var queue = musicManager.scheduler.getQueue();

        if (playingTrack == null) {
            channel.sendMessage("`Não há nenhuma faixa sendo reproduzida no momento.`").queue();
            return;
        }
        
        event.getMessage().delete().queue();

        var advancedAudioTrackNow = musicManager.scheduler.advancedAudioTrackNow;
        advancedAudioTrackNow.setAudioTrack(playingTrack);
        musicManager.scheduler.updateMessageIfIsNull(channel);
        musicManager.scheduler.dispatchPlayMessage(advancedAudioTrackNow);

        if (queue.isEmpty()) {
            return;
        }

        var embedBuilder = new EmbedBuilder()
                .setTitle("Fila de Músicas")
                .setColor(Color.GREEN)
                .setTimestamp(ZonedDateTime.now());

        var trackCount = Math.min(queue.size(), 10);
        var advancedAudioTrackList = new ArrayList<>(queue);

        for (int i = 0; i <  trackCount; i++) {
            var advancedAudioTrack = advancedAudioTrackList.get(i);
            var track = advancedAudioTrack.getAudioTrack();
            var info = track.getInfo();
            
            embedBuilder.addField(
                    String.format("#%s %s %s", (i + 1), advancedAudioTrack.getUsername(), PlayerController.formatTime(track.getDuration())),
                    String.format("[%s](%s)", info.title, track.getInfo().uri), 
                    false);
        }

        if (advancedAudioTrackList.size() > trackCount) {
            embedBuilder.setFooter(String.format("e mais %s...", advancedAudioTrackList.size() - trackCount));
        }
        channel.sendMessage(embedBuilder.build()).queue();
    }

}
