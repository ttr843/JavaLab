package handler;

import Dto.UserJwtDto;
import context.Component;
import lombok.NoArgsConstructor;
import payload.RegistrationPayload;
import helper.JWTUtils;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UserRepository;
import repository.UserRepositoryJdbcImpl;

import java.sql.Connection;
@NoArgsConstructor
public class RegistrationHandler implements Handler, Component {
    private UserRepository userRepository;
    private JWTUtils jwtUtils;

    public UserJwtDto handle(RegistrationPayload payLoad)  {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String login = payLoad.getLogin();
        String role = payLoad.getRole();
        String password =encoder.encode(payLoad.getPassword());
        userRepository.save(new User(0, login,
                password, role));
        UserJwtDto user = UserJwtDto.from(userRepository.findByLogin(login));
        UserJwtDto.setJwt(jwtUtils.getJWT(userRepository.findByLogin(login)));
        return user;
    }
}
