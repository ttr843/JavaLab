package ru.javalab.socketsshop.JSONProtocol;

public class SeeProductsPayload {
    private String token;

    public SeeProductsPayload() {

    }

    public SeeProductsPayload(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
