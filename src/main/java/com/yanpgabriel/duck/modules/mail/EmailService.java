package com.yanpgabriel.duck.modules.mail;

import com.yanpgabriel.duck.util.config.DuckApiConfig;
import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;

    @Inject
    DuckApiConfig duckApiConfig;

    public void send() {
        String dataHora = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
        mailer.send(
                Mail.withText(
                        duckApiConfig.getEmailAdmin(),
                        String.format("[Duck-API] Alerta! - %s", dataHora),
                        String.format("Este e-mail foi gerado as %s", dataHora)
                )
        );
    }

}
