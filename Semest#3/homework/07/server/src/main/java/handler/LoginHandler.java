package handler;

import Dto.UserDto;
import Dto.UserJwtDto;
import context.Component;
import lombok.NoArgsConstructor;
import payload.LoginPayload;
import helper.JWTUtils;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UserRepository;
import repository.UserRepositoryJdbcImpl;
import service.UserService;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

@NoArgsConstructor
public class LoginHandler implements Handler, Component {
    private UserRepository userRepository;
    private UserService userService;
    private JWTUtils jwtUtils;

    public UserJwtDto handle(LoginPayload payLoad)  {
        String login = payLoad.getLogin();
        String password =payLoad.getPassword();
        if (!userService.checkUser(login)) {
            return null;
        }
        else {
            try {
                if (userService.checkPassword(login, password)) {
                    User user = userRepository.findByLogin(login);
                    UserJwtDto.setJwt(jwtUtils.getJWT(user));
                    return UserJwtDto.from(user);
                } else {
                    return null;
                }
            } catch (SQLException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
