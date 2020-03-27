package ru.javalab.servletshop.service;

import ru.javalab.servletshop.model.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts();
}
