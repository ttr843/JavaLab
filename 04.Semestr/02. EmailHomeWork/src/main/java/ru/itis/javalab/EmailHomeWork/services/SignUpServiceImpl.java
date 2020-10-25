package ru.itis.javalab.EmailHomeWork.services;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.javalab.EmailHomeWork.dto.SignUpDto;
import ru.itis.javalab.EmailHomeWork.models.User;
import ru.itis.javalab.EmailHomeWork.repositories.UserRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;



    @Override
    public void signUp(SignUpDto form,Template template) {
        User user = User.builder()
                .email(form.getEmail())
                .password(form.getPassword())
                .name(form.getName())
                .confirmCode(UUID.randomUUID().toString())
                .build();
        userRepository.save(user);
        Map model = new HashMap();
        model.put("usermail",user.getEmail());
        model.put("link", "http://localhost:8080/confirm?confirmCode=" + user.getConfirmCode());
        try {
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            emailService.sendMail("Confirm", html, user.getEmail());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
