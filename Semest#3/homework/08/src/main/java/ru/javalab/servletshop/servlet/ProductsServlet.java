package ru.javalab.servletshop.servlet;

import ru.javalab.context.ApplicationContext;
import ru.javalab.servletshop.Helpers.Helper;
import ru.javalab.servletshop.model.Product;
import ru.javalab.servletshop.service.ProductServiceImpl;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsServlet extends HttpServlet {
    private ApplicationContext context;
    Map<String, Object> root = new HashMap<>();

    @Override
    public void init(ServletConfig config) throws ServletException {
        ServletContext servletContext = config.getServletContext();
        this.context = (ApplicationContext) servletContext.getAttribute("context");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ProductServiceImpl productService = context.getComponent(ProductServiceImpl.class);
        List<Product> products = productService.getProducts();
        root.put("products",products);
        Helper.render(req,resp,"products.ftl",root);
    }
}
