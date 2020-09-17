package handler;

import context.Component;
import model.Product;
import payload.DeleteProductPayload;
import repository.ProductRepository;
import repository.ProductRepositoryJdbcImpl;

import java.sql.Connection;

public class DeleteProductHandler implements Handler, Component {

    private ProductRepository productRepository;
    public void handle(int id) {
        productRepository.delete(productRepository.findByID(id));
    }
}
