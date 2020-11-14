package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab.ttr.hateoas.models.Genre;
import ru.itis.javalab.ttr.hateoas.models.Resource;
import ru.itis.javalab.ttr.hateoas.models.User;

import java.util.List;

@RepositoryRestResource
public interface UsersRepository extends JpaRepository<User,Long> {

    @RestResource(path = "byNameSurnameCountry" , rel = "NameSurnameCountry")
    User findByFirstNameAndLastNameAndCountry(String name,String surname,String country);

}
