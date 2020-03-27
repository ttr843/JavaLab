package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Token {
    long id;
    String role;

    public static Token from(User user) {
        return new Token(user.getId(), user.getRole());
    }

}
