package ru.itis.javalab.FakeInstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.javalab.FakeInstagram.model.State;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.UserRepository;

import java.util.Optional;

@Service
public class ConfirmServiceImpl implements ConfirmService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean confirm(String confirmCode) {
        Optional<User> userOptional = userRepository.findByConfirmCode(confirmCode);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            userRepository.updateStatus(user.getId(), State.CONFIRMED.toString());
            return true;
        }
        return false;
    }
}
