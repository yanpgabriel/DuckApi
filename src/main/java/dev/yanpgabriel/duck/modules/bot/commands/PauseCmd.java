package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.BotSocket;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class PauseCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getChannel();

        BotUtils.checkInVoiceChannel(event);
        BotUtils.checkSameVoiceChannel(event);

        var musicManager = PlayerController.getGuildAudioPlayer(event.getGuild(), channel);
        var paused = !musicManager.player.isPaused();
        musicManager.player.setPaused(paused);
        event.getMessage().addReaction(BotReaction.TOPPEN.toString()).queue();
        channel.sendMessage(String.format("A faixa foi %s.", paused ? "pausada" : "despausada")).queue();
    }

    @Override
    public void runSocket(BotSocket socket, Guild guild, String... args) {
        var selfVoiceState = guild.getSelfMember().getVoiceState();
        if (!selfVoiceState.inVoiceChannel()) {
            socket.broadcast("Eu preciso estar em um canal de voz para que isso funcione.");
            return;
        }
        var musicManager = PlayerController.getGuildAudioPlayerById(guild.getId());
        var paused = !musicManager.player.isPaused();
        musicManager.player.setPaused(paused);
        socket.broadcast(String.format("A faixa foi %s.", paused ? "pausada" : "despausada"));
    }
}
