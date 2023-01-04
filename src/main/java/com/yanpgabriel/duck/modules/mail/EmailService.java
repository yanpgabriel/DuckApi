package com.yanpgabriel.duck.modules.mail;

import io.quarkus.mailer.Mail;
import io.quarkus.mailer.Mailer;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.text.SimpleDateFormat;
import java.util.Date;

@ApplicationScoped
public class EmailService {

    @Inject
    Mailer mailer;

    @ConfigProperty(name = "duck.mail.admin")
    String emailAdmin;

    public void send() {
        String dataHora = new SimpleDateFormat("dd/MM/YYYY HH:mm:ss").format(new Date());
        mailer.send(
                Mail.withText(
                        emailAdmin,
                        String.format("[Duck-API] Alerta! - %s", dataHora),
                        String.format("Este e-mail foi gerado as %s", dataHora)
                )
        );
    }

}
