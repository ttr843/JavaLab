package ru.javalab.servletshop.service;

import ru.javalab.context.Component;
import ru.javalab.servletshop.model.Product;
import ru.javalab.servletshop.repository.ProductRepositoryImpl;

import java.util.List;

public class ProductServiceImpl implements ProductService, Component {
    private ProductRepositoryImpl productRepository;


    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }
}
