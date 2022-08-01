package dev.yanpgabriel.duck.modules.bot;

import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import dev.yanpgabriel.duck.modules.bot.utils.AdvancedAudioTrack;
import dev.yanpgabriel.duck.modules.bot.utils.MusicaDTO;
import net.dv8tion.jda.api.JDA;

import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Path("bot")
public class BotResource {
    
    @Inject
    BotService botService;

    @GET
    public String bot() {
        JDA jda = botService.getJDA();
        if (Objects.isNull(jda)) {
            return "Bot não foi iniciado!";
        }
        return jda.getSelfUser().getName();
    }

    @GET
    @Path("servers")
    public List<String> servers() {
        JDA jda = botService.getJDA();
        if (Objects.isNull(jda)) {
            return Collections.emptyList();
        }
        return jda.getGuilds().stream()
                .map(guild -> String.format("%s #%s", guild.getName(), guild.getId()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("room/{idServidor}")
    public List<String> room(@PathParam("idServidor") String idServidor) {
        JDA jda = botService.getJDA();
        if (Objects.isNull(jda)) {
            return Collections.emptyList();
        }
        return jda.getGuildById(idServidor).getVoiceChannels().stream()
                .map(voiceChannel -> String.format("%s #%s", voiceChannel.getName(), voiceChannel.getId()))
                .collect(Collectors.toList());
    }

    @GET
    @Path("queue/{idServidor}")
    public List<MusicaDTO> queue(@PathParam("idServidor") String idServidor) {
        final var guildMusicManager = PlayerController.getGuildAudioPlayerById(idServidor);
        final var player = guildMusicManager.player;
        final var playingTrack = player.getPlayingTrack();
        final var queue = guildMusicManager.scheduler.getQueue();

        if (playingTrack == null) {
            return Collections.emptyList();
        }

        var musicaDTOS = new ArrayList<MusicaDTO>();
        musicaDTOS.add(new MusicaDTO(playingTrack, player.isPaused()));
        musicaDTOS.addAll(queue.stream().map(AdvancedAudioTrack::getAudioTrack).map(MusicaDTO::new).collect(Collectors.toList()));
        return musicaDTOS;
    }

    @GET
    @Path("toggleMessage")
    public Response toggleMessage() {
        botService.updateMessage = !botService.updateMessage;
        return Response.noContent().build();
    }

    @GET
    @Path("start")
    public Response start() throws LoginException, InterruptedException {
        if (botService.checkIfItsAlive()) {
            return Response.status(Response.Status.BAD_GATEWAY).entity("Bot já foi iniciado!").build();
        }
        botService.init();
        return Response.ok().build();
    }
    
    @GET
    @Path("check")
    public Response check() {
        return Response.ok(botService.checkIfItsAlive()).build();
    }

    @GET
    @Path("stop")
    public Response stop() {
        JDA jda = botService.getJDA();
        if (!Objects.isNull(jda) && !jda.getStatus().equals(JDA.Status.SHUTDOWN)) {
            botService.shutdown();
            return Response.ok().build();
        }
        return Response.status(Response.Status.BAD_GATEWAY).entity("Bot já foi parado!").build();
    }
    
}
