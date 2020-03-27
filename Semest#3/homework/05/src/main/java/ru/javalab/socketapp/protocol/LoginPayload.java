package ru.javalab.socketapp.protocol;

public class LoginPayload {
    private String name,password;

    public LoginPayload() {

    }

    public LoginPayload(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
