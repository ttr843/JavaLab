package ru.itis.repositories;

import org.springframework.stereotype.Component;
import ru.itis.models.JlmqConsumer;
import ru.itis.models.MyQueue;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Component
public class JlmqConsumerRepositoryImpl implements JlmqConsumerRepository {

    @PersistenceContext
    private EntityManager entityManagerFactory;

    //language=HQL
    private static final String HQL_FIND_ALL =  "FROM JlmqConsumer j";

    @Override
    public Optional<JlmqConsumer> find(Long id) {
        return Optional.ofNullable(entityManagerFactory.find(JlmqConsumer.class, id));
    }

    @Override
    public List<JlmqConsumer> findAll() {
        return entityManagerFactory.createQuery(HQL_FIND_ALL).getResultList();
    }

    @Override
    public void save(JlmqConsumer entity) {
        entityManagerFactory.persist(entity);
    }

    @Override
    public void delete(Long id) {
        JlmqConsumer jlmqConsumer = entityManagerFactory.find(JlmqConsumer.class, id);
        entityManagerFactory.remove(jlmqConsumer);
    }
}
