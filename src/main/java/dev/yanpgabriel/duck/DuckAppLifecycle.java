package dev.yanpgabriel.duck;

import dev.yanpgabriel.duck.modules.bot.BotService;
import dev.yanpgabriel.duck.util.DuckApiConfig;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.security.auth.login.LoginException;
import java.sql.SQLException;

@ApplicationScoped
public class DuckAppLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger("DuckAppLifecycle");

    @Inject
    Flyway flyway;
    
    @Inject
    DuckApiConfig duckApiConfig;
    
    @Inject
    BotService botService;
    
    Server server;
    
    void onStart(@Observes StartupEvent event) throws SQLException {
        LOGGER.info("A aplicação iniciou usando o profile: {}", ProfileManager.getActiveProfile());
        
        LOGGER.info("Inicializando H2...");
        server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
        LOGGER.info("===================");

        if (duckApiConfig.isFlywayEnabled()) {
            LOGGER.info("Inicializando BD com Flyway...");
            flyway.clean();
            flyway.migrate();
            MigrationInfo current = flyway.info().current();
            if (current != null) {
                LOGGER.info(String.valueOf(current.getVersion()));
                LOGGER.info(String.valueOf(current.getState()));
            } else {
                LOGGER.info("SEM VERSÃO DE FLYWAY");
            }
            LOGGER.info("Flyway finalizado.");
        }

        LOGGER.info("Inicializando BOT...");
        if (botService.getInitOnStartUp()) {
            try {
                botService.init();
            } catch (LoginException e) {
                LOGGER.error("Bot não iniciado: Falha no login!");
            } catch (InterruptedException e) {
                LOGGER.error("Bot não iniciado: Falha generica.");
            }
        }
    }
    void onStop(@Observes ShutdownEvent event) {
        botService.shutdown();
        server.stop();
    }
}
