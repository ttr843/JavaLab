package ru.javalab.socketsshop.JSONProtocol;

public class DeleteProductPayload {
    private String nameOfProduct;
    private String token;

    public DeleteProductPayload(String nameOfProduct, String token) {
        this.nameOfProduct = nameOfProduct;
        this.token = token;
    }

    public String getNameOfProduct() {
        return nameOfProduct;
    }

    public void setNameOfProduct(String nameOfProduct) {
        this.nameOfProduct = nameOfProduct;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
