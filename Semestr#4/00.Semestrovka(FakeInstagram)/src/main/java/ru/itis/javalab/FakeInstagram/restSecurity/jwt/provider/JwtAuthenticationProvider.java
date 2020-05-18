package ru.itis.javalab.FakeInstagram.restSecurity.jwt.provider;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.omg.CORBA.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.itis.javalab.FakeInstagram.restSecurity.jwt.authentication.JwtAuthentication;
import ru.itis.javalab.FakeInstagram.restSecurity.jwt.details.UserDetailsImpl;



@Component("JwtAuthenticationProvider")
public class JwtAuthenticationProvider implements AuthenticationProvider {



    @Value("${jwt.secret}")
    private String secret;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token = authentication.getName();

        Claims claims;
        try {
            claims =  Jwts.parser().setSigningKey(secret).parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Bad token");
        }
        UserDetails userDetails = UserDetailsImpl.builder()
                .userId(Long.parseLong(claims.get("sub", String.class)))
                .role(claims.get("role", String.class))
                .name(claims.get("name", String.class))
                .surname(claims.get("surname", String.class))
                .email(claims.get("email", String.class))
                .hashPassword(claims.get("hashPassword", String.class))
                .photoPath(claims.get("photoPath", String.class))
                .build();
        authentication.setAuthenticated(true);
        ((JwtAuthentication)authentication).setUserDetails(userDetails);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return JwtAuthentication.class.equals(authentication);
    }
}
