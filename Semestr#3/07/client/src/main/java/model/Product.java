package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private String name;

    private int price, id;


    public Product(String name, int price) {
        this.id = 0;
        this.name = name;
        this.price = price;
    }

}
