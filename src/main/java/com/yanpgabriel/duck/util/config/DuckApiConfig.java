package com.yanpgabriel.duck.util.config;

import jakarta.inject.Singleton;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@Singleton
public class DuckApiConfig {

    @ConfigProperty(name = "duck.flyway")
    boolean flywayEnabled;

    @ConfigProperty(name = "duck.mail.admin")
    String emailAdmin;

    public boolean isFlywayEnabled() {
        return flywayEnabled;
    }

    public String getEmailAdmin() {
        return emailAdmin;
    }
}
