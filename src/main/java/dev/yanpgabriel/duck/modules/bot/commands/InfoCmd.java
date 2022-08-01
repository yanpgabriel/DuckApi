package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotSocket;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.stream.Collectors;

@BotCommand
public class InfoCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var channel = event.getTextChannel();
        channel.sendMessage(String.format("Id do Servidor: %s", event.getGuild().getId())).queue();
        channel.sendMessage(String.format("Id do canal: %s", channel.getId())).queue();
    }

    @Override
    public void runSocket(BotSocket socket, Guild guild, String... args) {
        var voiceChannels = guild.getVoiceChannels().stream()
                .map(GuildChannel::getName)
                .collect(Collectors.toList());
        var sb = new StringBuilder();
        sb.append("List de canais de voz:");
        voiceChannels.forEach(vc -> sb.append("\n-> ").append(vc));
        socket.broadcast(sb.toString());
    }
}
