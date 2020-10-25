package ru.itis.javalab.websockethomework.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import org.springframework.web.util.WebUtils;
import ru.itis.javalab.websockethomework.dto.UserDto;
import ru.itis.javalab.websockethomework.service.UserService;

import java.util.Map;
import java.util.Optional;

@Component
public class AuthHandler implements HandshakeInterceptor {


    @Autowired
    private UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> map) throws Exception {
        ServletServerHttpRequest request = (ServletServerHttpRequest) serverHttpRequest;
        String token = WebUtils.getCookie(request.getServletRequest(), "token").getValue();
        if (token == null) {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }
        Optional<UserDto> optionalUser = userService.check(token);
        if (optionalUser.isPresent()) {
            map.put("user", optionalUser.get());
            return true;
        } else {
            serverHttpResponse.setStatusCode(HttpStatus.FORBIDDEN);
            return false;
        }

    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
}
