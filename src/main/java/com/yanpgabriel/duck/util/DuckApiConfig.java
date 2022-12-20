package com.yanpgabriel.duck.util;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "duckapiconfig")
public class DuckApiConfig {
    
    boolean flywayEnabled;

    public boolean isFlywayEnabled() {
        return flywayEnabled;
    }

    public void setFlywayEnabled(boolean flywayEnabled) {
        this.flywayEnabled = flywayEnabled;
    }
}
