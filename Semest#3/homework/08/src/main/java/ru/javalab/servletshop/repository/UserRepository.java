package ru.javalab.servletshop.repository;

import ru.javalab.servletshop.model.User;

public interface UserRepository extends CrudRepository<User,Integer> {

    User findByName(String name);
}
