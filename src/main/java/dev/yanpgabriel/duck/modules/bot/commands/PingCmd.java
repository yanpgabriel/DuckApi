package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class PingCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        event.getMessage().addReaction(BotReaction.TOPPEN.toString()).queue();
        var time = System.currentTimeMillis();
        event.getChannel().sendMessage("Calculando...")
                .queue(response -> response.editMessageFormat("Meu ping atual: %d ms", System.currentTimeMillis() - time).queue());
    }

}
