package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.itis.javalab.ttr.hateoas.models.Resource;

import java.util.List;

@RepositoryRestResource
public interface ResourcesRepository extends JpaRepository<Resource,Long> {

    @RestResource(path = "maxRate",rel = "maxRate")
    @Query("from Resource resource where resource.rate = (select max(rate) from resource)")
    Resource findMaxRate();


    @RestResource(path = "byGenre" , rel = "genre")
    List<Resource> findAllByGenre(String genre);

}
