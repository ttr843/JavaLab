package ru.javalab.servletshop.service;

import ru.javalab.context.Component;
import ru.javalab.servletshop.Helpers.PasswordHasher;
import ru.javalab.servletshop.dto.UserDto;
import ru.javalab.servletshop.model.User;
import ru.javalab.servletshop.repository.UserRepositoryImpl;

public class SignUpServiceImpl implements SignUpService, Component {
    private UserRepositoryImpl userRepository;

    public SignUpServiceImpl() {
    }
    @Override
    public UserDto signUp(String login, String password) {
        User user = userRepository.findByName(login);
        if(user != null) {
            if(PasswordHasher.checkPassword(password,user.getPassword())) {
                return new UserDto(user.getId(),user.getLogin());
            }
        }else {
            userRepository.save(new User(login,password));
            User savedUser = userRepository.findByName(login);

            return new UserDto(savedUser.getId(),savedUser.getLogin());
        }
        return null;
    }
}
