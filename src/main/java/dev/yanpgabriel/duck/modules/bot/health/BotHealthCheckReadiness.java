package dev.yanpgabriel.duck.modules.bot.health;

import dev.yanpgabriel.duck.modules.bot.BotService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Readiness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@Readiness
@ApplicationScoped
public class BotHealthCheckReadiness implements HealthCheck {
    
    @Inject
    BotService botService;
    
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Discord bot health check read");
        if (botService.checkIfItsAlive()) {
            responseBuilder.up();
        } else {
            responseBuilder.down();
        }
        return responseBuilder.build();
    }
    
}
