package helper;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import context.Component;
import lombok.NoArgsConstructor;
import model.Token;
import model.User;

@NoArgsConstructor
public class JWTUtils implements Component {

    private String secret = "secret";
    private Algorithm algorithm = Algorithm.HMAC256(secret);


    public Token get(String token) {
        DecodedJWT jwt = JWT.require(algorithm)
                .build()
                .verify(token);

        long id = jwt.getClaim("id").asLong();
        String role = jwt.getClaim("role").asString();

        return new Token(id, role);
    }

    public boolean isAdmin(String token) {
        DecodedJWT decodedJWT = decoderJWT(token);
        if(decodedJWT.getClaim("role").equals("admin")){
            return  true;
        }
        return false;
    }

    public String getJWT(Token token) {
        return JWT.create()
                .withClaim("id", token.getId())
                .withClaim("role", token.getRole())
                .sign(algorithm);
    }

    public String getJWT(User user) {
        return getJWT(Token.from(user));
    }


    public DecodedJWT decoderJWT(String token) {
        DecodedJWT decodedJWT =  JWT.decode(token);
        return decodedJWT;
    }
}