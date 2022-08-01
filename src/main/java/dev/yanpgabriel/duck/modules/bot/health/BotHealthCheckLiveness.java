package dev.yanpgabriel.duck.modules.bot.health;

import dev.yanpgabriel.duck.modules.bot.BotService;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.HealthCheckResponseBuilder;
import org.eclipse.microprofile.health.Liveness;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Objects;

@Liveness
@ApplicationScoped
public class BotHealthCheckLiveness implements HealthCheck {
    
    @Inject
    BotService botService;
    
    @Override
    public HealthCheckResponse call() {
        HealthCheckResponseBuilder responseBuilder = HealthCheckResponse.named("Discord bot health check live");
        if (Objects.nonNull(botService.getJDA())) {
            responseBuilder
                    .up()
                    .withData("Nome:", botService.getJDA().getSelfUser().getName())
                    .withData("Status:", String.valueOf(botService.getJDA().getStatus()))
            ;
        } else {
            responseBuilder.down();
        }
        return responseBuilder.build();
    }
    
}
