package dev.yanpgabriel.duck.modules.bot.commands;


import dev.yanpgabriel.duck.modules.bot.BotSocket;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class AbstractCmd {
    
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String cmdName) {
        this.name = cmdName;
    }

    public void run(MessageReceivedEvent event, String... args) {
    }

    public void runSocket(BotSocket socket, Guild guild, String... args) {
    }

}

