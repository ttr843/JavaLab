package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.FakeInstagram.model.Document;
import ru.itis.javalab.FakeInstagram.repository.interfaces.DocumentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class DocumentRepositoryImpl implements DocumentRepository {

    private static final String HQL_FIND_ALL = "SELECT f FROM Document f";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Document entity) {
        entityManager.persist(entity);
    }


    @Override
    @Transactional
    public List findAll() {
        return entityManager.createQuery(HQL_FIND_ALL).getResultList();
    }
}
