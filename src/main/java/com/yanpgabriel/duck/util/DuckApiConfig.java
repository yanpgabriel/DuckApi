package com.yanpgabriel.duck.util;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
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
