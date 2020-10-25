package handler;

import Dto.ProductsDto;
import context.Component;
import lombok.NoArgsConstructor;
import payload.JsonObject;
import payload.ProductsPayload;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Product;
import repository.ProductRepository;
import repository.ProductRepositoryJdbcImpl;

import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

@NoArgsConstructor
public class GetProductsCommandHandler implements Handler, Component {
    private ProductRepository productRepository;
    public ProductsDto handle () {
        List<Product> products = productRepository.findAll();
        return ProductsDto.from(products);
    }
}
