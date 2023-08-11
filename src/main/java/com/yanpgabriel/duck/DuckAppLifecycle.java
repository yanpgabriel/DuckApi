package com.yanpgabriel.duck;

import com.yanpgabriel.duck.util.config.DuckApiConfig;
import com.yanpgabriel.duck.util.config.HibernateConfig;
import io.quarkus.runtime.ShutdownEvent;
import io.quarkus.runtime.StartupEvent;
import io.quarkus.runtime.configuration.ProfileManager;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.MigrationInfo;
import org.h2.tools.Server;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

@ApplicationScoped
public class DuckAppLifecycle {

    private static final Logger LOGGER = LoggerFactory.getLogger("DuckAppLifecycle");

    @Inject
    Flyway flyway;

    @Inject
    DuckApiConfig duckApiConfig;

    @Inject
    HibernateConfig hibernateConfig;

    Server server;
    
    void onStart(@Observes StartupEvent event) throws SQLException {
        LOGGER.info("================================================================================================");
        String activeProfile = ProfileManager.getActiveProfile();
        LOGGER.info("# A aplicação iniciou usando o profile: {}", activeProfile);
        LOGGER.info("# Hibernate scripts: {}", hibernateConfig.sqlLoadScript);
        LOGGER.info("# Hibernate Database Generation: {}", hibernateConfig.databaseGeneration);

        if (activeProfile.equalsIgnoreCase("dev")) {
            LOGGER.info("Inicializando H2...");
            server = Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
            LOGGER.info("H2 Iniciado");
        }

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
        LOGGER.info("================================================================================================");
    }
    void onStop(@Observes ShutdownEvent event) {
        server.stop();
    }
}
