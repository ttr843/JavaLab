package ru.itis.javalab.ttr.rabbitmqsecond.repository;

public interface CrudRepository<T,E> {
    void save(E entity);
    void delete(E entity);
}
