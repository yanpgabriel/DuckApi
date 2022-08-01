package dev.yanpgabriel.duck.modules.bot.discord;

import dev.yanpgabriel.duck.modules.bot.Bot;
import dev.yanpgabriel.duck.modules.bot.BotReaction;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageReaction;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class MessageReceivedEvent extends ListenerAdapter {

    // http://unicode.org/emoji/charts/full-emoji-list.html reaction
    @Override
    public void onMessageReceived(net.dv8tion.jda.api.events.message.MessageReceivedEvent event) {
        Message msg = event.getMessage();
        String[] split = msg.getContentRaw().split(" ", 2);
        String cmd = split[0].toLowerCase();

        if (!cmd.startsWith(Bot.PREFIX)) {
            return;
        }
        
        Bot.getInstance().getComandos()
                .stream()
                .filter(comando -> StringUtils.equalsIgnoreCase(comando.getName(), cmd.substring(1)))
                .findFirst()
                .ifPresent(comando -> {
                    try {
                        comando.run(event, split);
                    } catch (Exception e) {
                        String errorMessage = Objects.nonNull(e.getMessage()) ? e.getMessage() : "Erro desconhecido.";
                        event.getMessage().addReaction(BotReaction.DEDO_DO_MEIO.toString()).queue();
                        event.getChannel().sendMessage(errorMessage).queue();
                    }
                });

    }

    @Override
    public void onMessageReactionAdd(@NotNull MessageReactionAddEvent event) {
        MessageReaction reaction = event.getReaction();
        User user = event.getUser();
        assert user != null;

        // reaction.removeReaction(user).queue();
    }
}
