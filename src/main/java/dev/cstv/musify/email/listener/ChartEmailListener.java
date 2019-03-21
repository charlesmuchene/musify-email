package dev.cstv.musify.email.listener;

import dev.cstv.musify.email.service.EmailService;
import dev.cstv.musify.messaging.mail.Mail;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.util.Locale;

public class ChartEmailListener {
    @Autowired
    EmailService emailService;

    public void listen(Mail mail) {
        System.out.println("*** OK, I'm going to send chart email now... ***");

        try {
            emailService.sendChartMail(mail.getReceipientName(), mail.getReceipientEmail(), mail.getChartTitle(), mail.getSongs(), new Locale("en"));
        } catch (MessagingException e) {
            e.printStackTrace();
        }

        System.out.println("*** The Email has been sent, please check your email ***");
    }
}
