package ru.itis.javalab.FakeInstagram.repository.interfaces;

import ru.itis.javalab.FakeInstagram.model.Sub;
import ru.itis.javalab.FakeInstagram.repository.interfaces.CrudRepository;

import java.util.List;

public interface SubscriptionsRepository extends CrudRepository<Long, Sub> {

    void subscribe(long idToWho,long idWho);
    void deleteSub(long idToWho,long idWho);
    List<Sub> findAllSubs(long id);

}
