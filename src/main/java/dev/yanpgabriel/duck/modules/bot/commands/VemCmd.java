package dev.yanpgabriel.duck.modules.bot.commands;

import dev.yanpgabriel.duck.modules.bot.BotSocket;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

@BotCommand
public class VemCmd extends AbstractCmd {
    
    @Override
    public void run(MessageReceivedEvent event, String... args) {
        BotUtils.forcejoinVoiceChat(event, (unused -> {}));
    }

    @Override
    public void runSocket(BotSocket socket, Guild guild, String... args) {
        if (args.length < 2) {
            return;
        }
        var txt = args[1];
        guild.getVoiceChannels().stream()
                .filter(vc -> vc.getName().equalsIgnoreCase(txt))
                .findFirst()
                .ifPresent(vc -> {
                    if(!guild.getSelfMember().hasPermission(Permission.VOICE_CONNECT)) {
                        socket.broadcast("Não tenho permissão para entrar em chat de voz!");
                        return;
                    }
                    var audioManager = guild.getAudioManager();
                    audioManager.openAudioConnection(vc);
                    socket.broadcast("Entrei na sala '" + vc.getName() + "'.");
                });
    }
}
