package dev.yanpgabriel.duck.modules.bot;

import dev.yanpgabriel.duck.modules.bot.commands.AbstractCmd;
import dev.yanpgabriel.duck.modules.bot.discord.MessageReceivedEvent;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.BotCommand;
import io.quarkus.scheduler.Scheduled;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.security.auth.login.LoginException;
import java.lang.annotation.Annotation;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@ApplicationScoped
public class BotService {
    
    Logger logger = LoggerFactory.getLogger(BotService.class);
    
    @ConfigProperty(name = "discord.bot.token")
    String token;
    
    @ConfigProperty(name = "discord.bot.message.update")
    Boolean updateMessage;
    
    @ConfigProperty(name = "discord.bot.init-on-startup")
    Boolean initOnStartUp;
    
    public static Activity defaultActivity = Activity.streaming("nada o.O", "https://www.twitch.tv/yanznho");
    public static JDA jda;

    public JDA getJDA() {
        return jda;
    }
    
    public JDA init() throws LoginException, InterruptedException {
        if (Objects.isNull(jda)) {
            logger.info("Bot iniciando...");
            jda = configureMemoryUsage(JDABuilder.createDefault(token)
                .setBulkDeleteSplittingEnabled(false)
                .setActivity(defaultActivity)
                .addEventListeners(new MessageReceivedEvent()))
                .build();
            registerCommands();
            logger.info("Bot em funcionamento!");
        } else {
            logger.info("Bot já iniciado!");
        }
        return jda.awaitReady();
    }

    public void shutdown() {
        if (Objects.isNull(jda)) {
            logger.info("Bot ja esta offline.");
            return;
        }
        try {
            PlayerController.shutdown();
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            logger.error("Falha no Thread.sleep");
        }
        jda.getPresence().setIdle(true);
        jda.shutdownNow();
        jda = null;
    }
    
    public boolean checkIfItsAlive() {
        return Objects.nonNull(this.getJDA()) && this.getJDA().getStatus().equals(JDA.Status.CONNECTED);
    }

    public JDABuilder configureMemoryUsage(JDABuilder builder) {
        return builder
        .disableCache(CacheFlag.ACTIVITY)
        .setMemberCachePolicy(MemberCachePolicy.VOICE.or(MemberCachePolicy.OWNER))
        .setChunkingFilter(ChunkingFilter.NONE)
        .disableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_MESSAGE_TYPING)
        .setLargeThreshold(50);
    }
    
    @Scheduled(every="10s")
    public void updateTrackInfoInChat() {
        if (Boolean.TRUE.equals(updateMessage)) {
            PlayerController.getAllGuildAudioPlayers().forEach((guildId, guildMusicManager) -> {
                var trackScheduler = guildMusicManager.scheduler;
                var messageChannel = trackScheduler.messageChannel;
                if (Objects.isNull(messageChannel)) {
                    return;
                }
                var audioPlayer = guildMusicManager.player;
                var track = audioPlayer.getPlayingTrack();
                if (Objects.isNull(track)) {
                    return;
                }
                var advancedAudioTrackNow = trackScheduler.advancedAudioTrackNow;
                advancedAudioTrackNow.setAudioTrack(track);
                trackScheduler.dispatchPlayMessage(advancedAudioTrackNow);
            });
        }
    }

    public void registerCommands() {
        Set<AbstractCmd> commands = getInstantiatedClassesInPath("dev.yanpgabriel.duck.modules.bot.commands", BotCommand.class);
        commands.forEach(command -> {
            var className = command.getClass().getSimpleName();
            command.setName(StringUtils.removeEndIgnoreCase(className, "Cmd"));
        });
        Bot.getInstance().registrarComandos(commands);
    }

    public Boolean getInitOnStartUp() {
        return initOnStartUp;
    }

    private static <T> Set<T> getInstantiatedClassesInPath(String path, Class<? extends Annotation> classType) {
        var reflections = new Reflections(path);
        return reflections.getTypesAnnotatedWith(classType).stream()
                .filter(Objects::nonNull)
                .filter(clazz -> Objects.nonNull(clazz.getAnnotation(classType)))
                .map(clazz -> (T) createInstanceFromClass(clazz))
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());
    }

    private static <T> T createInstanceFromClass(Class<?> clazz) {
        try {
            return (T) clazz.getDeclaredConstructor().newInstance();
        }
        catch (Exception e) {
            // MessageUtils.sendDebugMessage("Falha ao criar instancia da classe '%s'.", clazz.getName());
            return null;
        }
    }
}
