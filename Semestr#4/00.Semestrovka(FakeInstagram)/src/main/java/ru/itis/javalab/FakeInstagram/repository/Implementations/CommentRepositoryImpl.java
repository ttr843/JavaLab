package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.FakeInstagram.model.Comment;
import ru.itis.javalab.FakeInstagram.repository.interfaces.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private static final String HQL_FIND_BY_POST_ID = "SELECT f FROM Comment f where f.idPost = ?1";

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Comment entity) {
        entityManager.persist(entity);
    }

    @Override
    @Transactional
    public List findAllCommentsOfPost(long id) {
        return entityManager.createQuery(HQL_FIND_BY_POST_ID).setParameter(1,id).getResultList();
    }


}
