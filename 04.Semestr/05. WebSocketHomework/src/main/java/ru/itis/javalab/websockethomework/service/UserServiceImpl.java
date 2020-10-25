package ru.itis.javalab.websockethomework.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import ru.itis.javalab.websockethomework.repository.UserRepository;
import ru.itis.javalab.websockethomework.dto.UserDto;
import ru.itis.javalab.websockethomework.model.User;
import ru.itis.javalab.websockethomework.util.PasswordEncoder;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private  Environment environment;
    @Autowired
    private UserRepository userRepository;


    @Override
    public void signUp(UserDto userDto) {
        if (!userRepository.findUserByEmail(userDto.getEmail()).isPresent()) {
            userRepository.save(User.builder()
                    .email(userDto.getEmail())
                    .name(userDto.getName())
                    .passwordHash(passwordEncoder.hashPassword(userDto.getPassword()))
                    .build());
        }
    }

    @Override
    public void signIn(UserDto userDto) {
        Optional<User> userOptional = userRepository.findUserByEmail(userDto.getEmail());
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.checkPassword(userDto.getPassword(), user.getPasswordHash())) {
                Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("password.secret.key"));
                String token = JWT.create()
                        .withSubject(String.valueOf(user.getId()))
                        .sign(algorithm);
                userDto.setToken(token);
                userDto.setId(user.getId());
            }
        }
    }

    @Override
    public Optional<UserDto> check(String token) {
        Algorithm algorithm = Algorithm.HMAC256(environment.getProperty("password.secret.key"));
        try {
            JWTVerifier verifier = JWT.require(algorithm).build();
            Long userId = Long.valueOf(verifier.verify(token).getSubject());
            Optional<UserDto> optionalUser = findUserById(userId);
            if (optionalUser.isPresent()) {
                optionalUser.get().setToken(token);
                return optionalUser;
            } else {
                return Optional.empty();
            }
        } catch (JWTVerificationException exception) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.map(UserDto::from);
    }
}