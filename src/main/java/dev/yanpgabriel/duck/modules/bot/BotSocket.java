package dev.yanpgabriel.duck.modules.bot;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@ServerEndpoint("/bot/socket/{idServidor}")
public class BotSocket {

    @Inject
    BotService botService;
    
    private static final Logger LOG = Logger.getLogger(BotSocket.class);

    Map<String, Session> sessions = new ConcurrentHashMap<>();

    @OnOpen
    public void onOpen(Session session, @PathParam("idServidor") String idServidor) {
        sessions.put(idServidor, session);
        broadcast("Conectado ao servidor: " + idServidor);
    }

    @OnClose
    public void onClose(Session session, @PathParam("idServidor") String idServidor) {
        sessions.remove(idServidor);
        broadcast("Desconectado do servidor: " + idServidor);
    }

    @OnError
    public void onError(Session session, @PathParam("idServidor") String idServidor, Throwable throwable) {
        sessions.remove(idServidor);
        LOG.error("onError", throwable);
        broadcast("Desconectado do servidor: " + idServidor + " - Erro: [" + throwable + "]");
    }

    @OnMessage
    public void onMessage(String message, @PathParam("idServidor") String idServidor) {
        var split = message.split(" ", 2);
        var cmd = split[0].toLowerCase();
        
        if (!message.startsWith(Bot.PREFIX)) {
            broadcast("Use o prefix do bot!");
            return;
        }

        var guildById = botService.getJDA().getGuildById(idServidor);
        
        Bot.getInstance().getComandos()
                .stream()
                .filter(comando -> StringUtils.equalsIgnoreCase(comando.getName(), cmd.substring(1)))
                .findFirst()
                .ifPresentOrElse(comando -> {
                    try {
                        comando.runSocket(this, guildById, split);
                    } catch (Exception e) {
                        broadcast(e.getMessage());
                    }
                }, () -> broadcast("Não foi encontrado nenhum comando para: " + message));
    }

    public void broadcast(String message) {
        sessions.values().forEach(s -> {
            s.getAsyncRemote().sendObject(message, result -> {
                if (result.getException() != null) {
                    System.out.println("Unable to send message: " + result.getException());
                }
            });
        });
    }
    
}
