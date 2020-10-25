package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.ProductRep;

import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.withJDBCTemplate.repository.CrudRepositories;

import javax.sql.DataSource;

public interface ProductRepository extends CrudRepositories<Product, Integer> {

    public void setDataSource(DataSource dataSource);
}
