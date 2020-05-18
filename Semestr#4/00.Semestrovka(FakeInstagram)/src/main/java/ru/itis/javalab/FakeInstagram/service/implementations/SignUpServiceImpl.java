package ru.itis.javalab.FakeInstagram.service.implementations;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.Role;
import ru.itis.javalab.FakeInstagram.model.State;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.UploadService;
import ru.itis.javalab.FakeInstagram.service.interfaces.EmailService;
import ru.itis.javalab.FakeInstagram.service.interfaces.SignUpService;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class SignUpServiceImpl implements SignUpService {
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public SignUpServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired
    private EmailService emailService;

    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Autowired
    private UploadService uploadService;

    @Override
    public UserDto login(UserDto userDto) {
        return null;
    }

    @Override
    public UserDto signUp(UserDto userDto) {
        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .photoPath("../img/empty-photo.png")
                .confirm_code(UUID.randomUUID().toString())
                .role(Role.USER)
                .state(State.NOT_CONFIRMED)
                .build();
        userRepository.save(user);
        userDto.setId(user.getId());
        Map model = new HashMap();
        model.put("usermail",user.getEmail());
        model.put("link", "http://localhost/confirm?confirmCode=" + user.getConfirm_code());
        try {
            Template template = freeMarkerConfigurer.getConfiguration().getTemplate("mail.ftlh");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(template,model);
            emailService.sendMail("Confirm", html, user.getEmail());
        } catch (IOException e) {
            throw new IllegalStateException(e);
        } catch (TemplateException e) {
            throw new IllegalArgumentException(e);
        }
        return userDto;
    }
}
