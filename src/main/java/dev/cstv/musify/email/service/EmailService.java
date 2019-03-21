package dev.cstv.musify.email.service;

import java.util.List;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring4.SpringTemplateEngine;

@Service("emailService")
public class EmailService {

     private static final String LOGO_IMG = "templates/images/logo.png";
     private static final String PNG_MIME = "image/png";

    @Autowired
    private JavaMailSender mailSender;
 
    @Autowired
    private SpringTemplateEngine templateEngine;
    
    public void sendChartMail(final String recipientName, final String recipientEmail, final String chartTitle, final List<String> songs, final Locale locale)
            throws MessagingException {
        
        final Context thymeContext = new Context(locale);
        thymeContext.setVariable("name", recipientName);
        thymeContext.setVariable("chartTitle", chartTitle);
        thymeContext.setVariable("songs", songs);

        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true,"UTF-8");
        message.setSubject("Musify - " + chartTitle);
 
        message.setTo(recipientEmail);

        final String htmlContent = this.templateEngine.process("chartMail", thymeContext);
        message.setText(htmlContent, true);
   
        message.addInline("logo", new ClassPathResource(LOGO_IMG), PNG_MIME);

        this.mailSender.send(mimeMessage);
    }
}
