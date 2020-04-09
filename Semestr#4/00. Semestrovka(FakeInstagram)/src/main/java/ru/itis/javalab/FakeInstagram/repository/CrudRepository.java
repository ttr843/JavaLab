package ru.itis.javalab.FakeInstagram.repository;

public interface CrudRepository<T, E> {
    void save(E entity);

}
