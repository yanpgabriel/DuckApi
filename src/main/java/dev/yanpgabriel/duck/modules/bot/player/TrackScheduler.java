package dev.yanpgabriel.duck.modules.bot.player;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;
import dev.yanpgabriel.duck.modules.bot.BotReaction;
import dev.yanpgabriel.duck.modules.bot.BotService;
import dev.yanpgabriel.duck.modules.bot.utils.AdvancedAudioTrack;
import dev.yanpgabriel.duck.modules.bot.utils.BotUtils;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * This class schedules tracks for the audio player. It contains the queue of tracks.
 */
public class TrackScheduler extends AudioEventAdapter {
    public final AudioPlayer player;
    public final BlockingQueue<AdvancedAudioTrack> queue;
    public MessageChannel messageChannel;
    public Message message;
    public AdvancedAudioTrack advancedAudioTrackNow;

    /**
     * @param player The audio player this scheduler uses
     */
    public TrackScheduler(AudioPlayer player, MessageChannel messageChannel) {
        this.queue = new LinkedBlockingQueue<>();
        this.player = player;
        this.messageChannel = messageChannel;
    }

    public void updateMessageIfIsNull(MessageChannel messageChannel) {
        this.messageChannel = messageChannel;
    }

    private void setMessage(Message message) {
        this.message = message;
        this.message.addReaction(BotReaction.PLAY_PAUSE.toString()).queue();
        this.message.addReaction(BotReaction.NEXT.toString()).queue();
        this.message.addReaction(BotReaction.STOP.toString()).queue();
    }
    
    public BlockingQueue<AdvancedAudioTrack> getQueue() {
        return queue;
    }

    /**
     * Adicione a próxima faixa à fila ou reproduza imediatamente se não houver nada na fila.
     *
     * @param advancedAudioTrack A track para reproduzir ou adicionar na fila.
     */
    public void queue(AdvancedAudioTrack advancedAudioTrack) {
        if (!player.startTrack(advancedAudioTrack.getAudioTrack(), true)) {
            queue.offer(advancedAudioTrack);
            if (advancedAudioTrack.isPlaylist() && advancedAudioTrack.getPlaylistSize() <= 0) {
                return;
            }
            dispatchQueueMessage(advancedAudioTrack);
        } else {
            advancedAudioTrackNow = advancedAudioTrack;
            dispatchPlayMessage(advancedAudioTrack);
        }
    }

    /**
     * Start the next track, stopping the current one if it is playing.
     */
    public void nextTrack() {
        AdvancedAudioTrack poll = queue.poll();
        AudioTrack track = Objects.nonNull(poll) ? poll.getAudioTrack() : null;
        if (Objects.isNull(poll)) {
            if (Objects.nonNull(messageChannel)) {
                deleteInfoMessage();
                BotService.jda.getPresence().setActivity(BotService.defaultActivity);
                messageChannel.sendMessage("`Não há nenhuma faixa na fila.`").queue();
            }
            return;
        }

        if (player.startTrack(track, false)) {
            advancedAudioTrackNow = poll;
            dispatchPlayMessage(poll);
        }
    }

    @Override
    public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
        // Comece a próxima faixa apenas se o motivo final for adequado para ela (FINISHED ou LOAD_FAILED) 
        if (endReason.mayStartNext) {
            nextTrack();
        }
    }

    public void dispatchPlayMessage(AdvancedAudioTrack advancedAudioTrack) {
        MessageEmbed embed = BotUtils.playingMessageEmbed(advancedAudioTrack);
        deleteInfoMessage();
        BotService.jda.getPresence().setActivity(Activity.streaming(advancedAudioTrack.getAudioTrack().getInfo().title, advancedAudioTrack.getAudioTrack().getInfo().uri));
        if (Objects.nonNull(this.messageChannel)) {
            this.messageChannel.sendMessage(embed).queue(this::setMessage);
        }
    }

    public void dispatchQueueMessage(AdvancedAudioTrack advancedAudioTrack) {
        MessageEmbed embed = BotUtils.queueMessageEmbed(advancedAudioTrack);
        messageChannel.sendMessage(embed).queue();
        System.out.println("`adicionou uma faixa na lista de reprodução.`");
    }

    public void deleteInfoMessage() {
        if (Objects.nonNull(this.message)) {
            this.message.delete().queue();
            this.message = null;
        }
    }
}
