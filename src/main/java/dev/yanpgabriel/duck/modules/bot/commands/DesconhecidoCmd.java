package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class DesconhecidoCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        var msg = event.getMessage();
        msg.addReaction("\uD83E\uDD14").queue();
        event.getChannel().sendMessage("Comando não encontrado!").queue();
    }

}
