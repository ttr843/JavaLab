package Dto;

import model.User;

public class UserJwtDto implements ForOneDto{
    private int id;
    private String role;
    private static String jwt;

    public UserJwtDto(int id, String role, String jwt) {
        this.id = id;
        this.role = role;
        UserJwtDto.jwt = jwt;
    }

    public static String getJwt() {
        return jwt;
    }

    public static void setJwt(String jwt) {
        UserJwtDto.jwt = jwt;
    }

    public int getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public static UserJwtDto from(User user) {
        return new UserJwtDto(user.getId(), user.getRole(), getJwt());
    }

}
