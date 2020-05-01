package ru.itis.javalab.FakeInstagram.repository.interfaces;

public interface CrudRepository<T, E> {
    void save(E entity);

}
