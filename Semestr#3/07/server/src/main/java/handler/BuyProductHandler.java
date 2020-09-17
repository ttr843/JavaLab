package handler;

import context.Component;
import lombok.NoArgsConstructor;
import model.Product;
import payload.BuyProductPayload;
import repository.ProductRepository;
import repository.ProductRepositoryJdbcImpl;

import java.sql.Connection;
@NoArgsConstructor
public class BuyProductHandler implements Handler, Component {

    private ProductRepository productRepository;
    public void handle(Product product) {
        productRepository.delete(product);
    }
}
