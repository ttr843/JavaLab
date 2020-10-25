package Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.User;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserDto implements ForOneDto{

    private String login;
    private int id;
    public static UserDto from(User user) {
        return new UserDto(user.getLogin(), user.getId());
    }
}
