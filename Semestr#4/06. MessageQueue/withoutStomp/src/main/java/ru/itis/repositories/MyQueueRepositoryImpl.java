package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.MyQueue;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class MyQueueRepositoryImpl implements MyQueueRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL =  "FROM MyQueue m";

    //language=HQL
    private final static String HQL_FIND_BY_NAME = "SELECT m FROM MyQueue m WHERE m.queueName = ?1";

    @Override
    public Optional<MyQueue> findByName(String queueName) {
        MyQueue myQueue = null;
        try {
            myQueue = (MyQueue) entityManagerFactory
                    .createQuery(HQL_FIND_BY_NAME)
                    .setParameter(1, queueName)
                    .getSingleResult();
        }
        catch (NoResultException nre){
            //Ignore this because the logic this is ok!
        }
        return Optional.ofNullable(myQueue);
    }

    @Override
    public Optional<MyQueue> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(MyQueue.class, id));
    }

    @Override
    public List<MyQueue> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(MyQueue myQueue) {
        entityManagerFactory.persist(myQueue);
    }

    @Override
    public void delete(Long id) {
        MyQueue myQueue = entityManagerFactory.find(MyQueue.class, id);
        entityManagerFactory.remove(myQueue);
    }
}
