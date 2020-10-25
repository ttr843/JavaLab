package ru.itis.javalab.dbhomework.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private Long id;

    private String name;
    private String email;
    private String password;
    private ArrayList<Product> products;


    public User(Long id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.products = new ArrayList<>();
    }


    public void setProduct(Product product) {
    products.add(product);
    }
}
