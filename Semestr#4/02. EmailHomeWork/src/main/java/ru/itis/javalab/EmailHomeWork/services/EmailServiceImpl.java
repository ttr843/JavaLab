package ru.itis.javalab.EmailHomeWork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public void sendMail(String subject, String html, String mail) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("group801.11@gmail.com");
            messageHelper.setTo(mail);
            messageHelper.setSubject(subject);
            messageHelper.setText(html, true);
        };
        javaMailSender.send(messagePreparator);
    }
}
