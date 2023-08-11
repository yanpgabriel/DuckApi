package com.yanpgabriel.duck.util.config;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class HibernateConfig {

    @ConfigProperty(name = "quarkus.hibernate-orm.sql-load-script")
    public String sqlLoadScript;

    @ConfigProperty(name = "quarkus.hibernate-orm.database.generation")
    public String databaseGeneration;

}
