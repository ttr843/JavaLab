package ru.javalab.socketsshop.JSONProtocol;

import ru.javalab.socketsshop.database.ORM.Product;

import java.util.ArrayList;

public class SeeProductResponse {
    private ArrayList<Product> product;

    public SeeProductResponse() {

    }

    public SeeProductResponse(ArrayList<Product> product) {
        this.product = product;
    }

    public ArrayList<Product> getProduct() {
        return product;
    }

    public void setProduct(ArrayList<Product> product) {
        this.product = product;
    }
}
