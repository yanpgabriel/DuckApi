package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.BotSocket;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class SaiCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getChannel();
        var connectedChannel = event.getGuild().getSelfMember().getVoiceState().getChannel();
        if(connectedChannel == null) {
            event.getMessage().addReaction(BotReaction.DEDO_DO_MEIO.toString()).queue();
            channel.sendMessage("Não estou em nenhum chat de voz para sair '-'").queue();
            return;
        }
        event.getMessage().addReaction(BotReaction.TOPPEN.toString()).queue();
        event.getGuild().getAudioManager().closeAudioConnection();
    }

    @Override
    public void runSocket(BotSocket socket, Guild guild, String[] split) {
        var connectedChannel = guild.getSelfMember().getVoiceState().getChannel();
        if(connectedChannel == null) {
            socket.broadcast("Não estou em nenhum chat de voz para sair '-'");
            return;
        }
        guild.getAudioManager().closeAudioConnection();
    }
}
