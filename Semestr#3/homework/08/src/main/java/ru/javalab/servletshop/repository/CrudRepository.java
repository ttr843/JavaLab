package ru.javalab.servletshop.repository;


import java.util.List;
import java.util.Optional;

public interface CrudRepository<T,ID> {
    T findOne(ID id);
    List<T> findAll();
    void save(T model);
    void delete(T model);
    void update();
}
