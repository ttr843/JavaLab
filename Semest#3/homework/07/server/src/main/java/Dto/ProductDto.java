package Dto;

import model.Product;

public class ProductDto implements Dto{
    private Product product;

    public ProductDto() {
    }

    public ProductDto(Product product) {
        this.product = product;
    }

    public Product getProduct() {
        return product;
    }

    public static ProductDto from(Product product) {
        return new ProductDto(product);
    }
}
