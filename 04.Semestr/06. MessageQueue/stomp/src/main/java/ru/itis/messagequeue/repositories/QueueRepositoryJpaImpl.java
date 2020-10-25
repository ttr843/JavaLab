package ru.itis.messagequeue.repositories;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.messagequeue.models.jpa.MessageEntity;
import ru.itis.messagequeue.models.jpa.MyQueue;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class QueueRepositoryJpaImpl implements QueueRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;


    private final static String HQL_FIND_BY_NAME = "SELECT q FROM MyQueue q WHERE q.queueName = ?1";

    @Override
    public Optional<MyQueue> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<MyQueue> findAll() {
        return null;
    }

    @Override
    @Transactional
    public void save(MyQueue queue) {
        entityManagerFactory.persist(queue);
    }

    @Override
    public void delete(Long id) {
        MyQueue queue = entityManagerFactory.find(MyQueue.class, id);
        entityManagerFactory.remove(queue);
    }

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
}
