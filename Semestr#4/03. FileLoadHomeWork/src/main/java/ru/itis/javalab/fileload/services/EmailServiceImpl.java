package ru.itis.javalab.fileload.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment environment;

    @Override
    public void sendMail(String subject, String html, String email) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom(environment.getProperty("mail.username"));
            messageHelper.setTo(email);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };
        javaMailSender.send(messagePreparator);
    }
}
