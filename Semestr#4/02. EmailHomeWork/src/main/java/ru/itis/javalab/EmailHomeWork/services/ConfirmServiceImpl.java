package ru.itis.javalab.EmailHomeWork.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.javalab.EmailHomeWork.models.User;
import ru.itis.javalab.EmailHomeWork.repositories.UserRepository;

import java.util.Optional;

@Component
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean confirm(String confirmCode) {
        Optional<User> userOptional = userRepository.findByConfirmCode(confirmCode);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.updateStatus(user.getId(), true);
            return true;
        }
        return false;
    }
}
