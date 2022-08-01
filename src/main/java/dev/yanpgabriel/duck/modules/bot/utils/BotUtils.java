package dev.yanpgabriel.duck.modules.bot.utils;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackInfo;
import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.player.PlayerController;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

import java.awt.*;
import java.time.ZonedDateTime;
import java.util.Objects;
import java.util.function.Consumer;

public abstract class BotUtils {

    public static void joinVoiceChat(MessageReceivedEvent event, Consumer<Void> beforeJoin) {
        final GuildVoiceState selfVoiceState = event.getGuild().getSelfMember().getVoiceState();
        if (!selfVoiceState.inVoiceChannel()) {
            BotUtils.forcejoinVoiceChat(event, beforeJoin);
        } else if (Objects.nonNull(beforeJoin)) {
            beforeJoin.accept(null);
        }
    }

    public static void forcejoinVoiceChat(MessageReceivedEvent event, Consumer<Void> beforeJoin) {
        Message msg = event.getMessage();
        MessageChannel channel = event.getChannel();
        if(!event.getGuild().getSelfMember().hasPermission(Permission.VOICE_CONNECT)) {
            channel.sendMessage("Não tenho permissão para entrar em chat de voz!").queue();
            return;
        }
        VoiceChannel connectedChannel = Objects.requireNonNull(Objects.requireNonNull(event.getMember()).getVoiceState()).getChannel();
        if(connectedChannel == null) {
            msg.addReaction("\uD83D\uDD95").queue();
            channel.sendMessage("Você não esta em nenhum chat de voz!").queue();
            return;
        }
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(connectedChannel);
        event.getMessage().addReaction(BotReaction.TOPPEN.toString()).queue(beforeJoin);
    }

    public static void checkIamVoiceChannel(MessageReceivedEvent event) {
        final GuildVoiceState selfVoiceState = event.getGuild().getSelfMember().getVoiceState();
        if (!selfVoiceState.inVoiceChannel()) {
            throw new RuntimeException("Eu preciso estar em um canal de voz para que isso funcione.");
        }
    }

    public static void checkInVoiceChannel(MessageReceivedEvent event) {
        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        if (!memberVoiceState.inVoiceChannel()) {
            throw new RuntimeException("Você precisa estar em um canal de voz para que este comando funcione.");
        }
    }

    public static void checkSameVoiceChannel(MessageReceivedEvent event) {
        final Member member = event.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();
        final GuildVoiceState selfVoiceState = event.getGuild().getSelfMember().getVoiceState();
        if (Objects.isNull(memberVoiceState.getChannel()) || Objects.isNull(selfVoiceState.getChannel())) {
            throw new RuntimeException("Aparentemente os canais de audio estão nulos.");
        }
        if (!memberVoiceState.getChannel().getName().equalsIgnoreCase(selfVoiceState.getChannel().getName())) {
            throw new RuntimeException("Você precisa estar no mesmo canal de voz que eu para que isso funcione.");
        }
    }

    public static MessageEmbed queueMessageEmbed(AdvancedAudioTrack advancedAudioTrack) {
        AudioTrack audioTrack = advancedAudioTrack.getAudioTrack();
        AudioTrackInfo audioTrackInfo = audioTrack.getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle(String.format("Fila de Música | %s", advancedAudioTrack.getChannelName()))
                .setDescription("Adicionada na fila de reprodução!")
                .setColor(Color.GREEN)
                .setThumbnail(String.format("https://i.ytimg.com/vi/%s/hqdefault.jpg", audioTrack.getIdentifier()))
                .addField("Titulo:", String.format("`%s`", audioTrackInfo.title), false)
                .addField("URL:", String.format("[Abrir no Youtube](%s)", audioTrackInfo.uri), true)
                .addField("Duração:", String.format("`%s`", PlayerController.formatTime(audioTrack.getDuration())), true)
                .setFooter(String.format("Solicitado por %s", advancedAudioTrack.getUsername()), advancedAudioTrack.getAvatarUrl())
                .setTimestamp(ZonedDateTime.now());
        if (advancedAudioTrack.isSearch()) {
            embedBuilder.appendDescription("\n*(Primeiro resultado da busca no youtube)*");
        }
        if (advancedAudioTrack.isPlaylist()) {
            embedBuilder.appendDescription("\n*(Primeira faixa da playlist no youtube)*");
            embedBuilder.appendDescription(String.format("\n***%s | %s faixas***", advancedAudioTrack.getPlaylistName(), advancedAudioTrack.getPlaylistSize()));
        }
        return embedBuilder.build();
    }

    public static MessageEmbed playingMessageEmbed(AdvancedAudioTrack advancedAudioTrack) {
        AudioTrack audioTrack = advancedAudioTrack.getAudioTrack();
        AudioTrackInfo audioTrackInfo = audioTrack.getInfo();
        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle(String.format("Informações de Música | %s", advancedAudioTrack.getChannelName()), audioTrackInfo.uri)
                .setColor(Color.GREEN)
                .setImage(String.format("https://i.ytimg.com/vi/%s/maxresdefault.jpg", audioTrack.getIdentifier()))
                .setThumbnail(String.format("https://i.ytimg.com/vi/%s/hqdefault.jpg", audioTrack.getIdentifier()))
                .addField("Tocando:", String.format("`%s`", audioTrackInfo.title), false)
                .addField("URL:", String.format("[Abrir no Youtube](%s)", audioTrackInfo.uri), true)
                .addField("Duração:", String.format("`%s/%s`", PlayerController.formatTime(audioTrack.getPosition()), PlayerController.formatTime(audioTrack.getDuration())), true)
                .setFooter(String.format("Solicitado por %s", advancedAudioTrack.getUsername()), advancedAudioTrack.getAvatarUrl())
                .setTimestamp(ZonedDateTime.now());
        if (advancedAudioTrack.isPlaylist()) {
            String format = advancedAudioTrack.getPlaylistSize() > 0 ? 
                    String.format("%s | %s faixas", advancedAudioTrack.getPlaylistName(), advancedAudioTrack.getPlaylistSize()) : 
                    advancedAudioTrack.getPlaylistName();
            embedBuilder.appendDescription(String.format("***\n%s***", format));
        }
        return embedBuilder
                .build();
    }

}
