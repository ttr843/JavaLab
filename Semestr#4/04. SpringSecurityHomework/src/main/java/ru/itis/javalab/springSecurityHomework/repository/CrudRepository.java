package ru.itis.javalab.springSecurityHomework.repository;

public interface CrudRepository<T, E> {
    void save(E entity);

}
