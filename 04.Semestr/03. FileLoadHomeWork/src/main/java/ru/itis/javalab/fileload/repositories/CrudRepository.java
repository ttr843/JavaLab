package ru.itis.javalab.fileload.repositories;

import java.util.List;
import java.util.Optional;

public interface CrudRepository<ID, V> {
    Optional<V> find(ID id);
    List<V> findAll();
    void save(V entity);
    void delete(ID id);
    Optional<V> find(String name);
}
