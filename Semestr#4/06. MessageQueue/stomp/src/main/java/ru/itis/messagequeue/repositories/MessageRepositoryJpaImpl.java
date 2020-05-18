package ru.itis.messagequeue.repositories;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.messagequeue.models.jpa.MessageEntity;
import ru.itis.messagequeue.models.jpa.MyQueue;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class MessageRepositoryJpaImpl implements MessageRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL = "From MessageEntity message";

    //language=HQL
    private final static String HQL_FIND_BY_QUEUE = "SELECT m FROM MessageEntity m WHERE m.queue = ?1";

    @Override
    public Optional<MessageEntity> find(String id) {
        return Optional.ofNullable(entityManagerFactory.find(MessageEntity.class, id));
    }

    @Override
    @Transactional
    public List<MessageEntity> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    @Transactional
    public void save(MessageEntity message) {
        entityManagerFactory.persist(message);
    }

    @Override
    public void delete(String id) {
        MessageEntity message = entityManagerFactory.find(MessageEntity.class, id);
        entityManagerFactory.remove(message);
    }

    @Override
    @Transactional
    public void update(MessageEntity message) {
        entityManagerFactory.merge(message);
    }

    @Override
    public List<MessageEntity> findAllByQueue(MyQueue queue) {
        List<MessageEntity> messages = new ArrayList<>();
        try {
            messages =  (List<MessageEntity>) entityManagerFactory
                    .createQuery(HQL_FIND_BY_QUEUE)
                    .setParameter(1, queue)
                    .getResultList();
        }
        catch (NoResultException nre){
            //Ignore this because the logic this is ok!
        }
        return messages;
    }
}
