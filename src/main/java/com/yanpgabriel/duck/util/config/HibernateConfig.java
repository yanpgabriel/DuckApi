package com.yanpgabriel.duck.util.config;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class HibernateConfig {

    private static final String NAO_DEFINIDO = "Não definido para esse profile";

    @ConfigProperty(name = "quarkus.hibernate-orm.sql-load-script", defaultValue = NAO_DEFINIDO)
    public String sqlLoadScript;

    @ConfigProperty(name = "quarkus.hibernate-orm.database.generation", defaultValue = NAO_DEFINIDO)
    public String databaseGeneration;

}
