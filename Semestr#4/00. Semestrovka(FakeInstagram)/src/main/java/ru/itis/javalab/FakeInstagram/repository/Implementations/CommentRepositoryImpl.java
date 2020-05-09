package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.javalab.FakeInstagram.model.Comment;
import ru.itis.javalab.FakeInstagram.repository.interfaces.CommentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private static final String SQL_FIND_BY_POST_ID = "SELECT * FROM comments where idPost = ?";

    private RowMapper<Comment> commentRowMapper = (row,rowNumber) ->
            Comment.builder()
            .id(row.getLong("id"))
            .namePublicator(row.getString("namePublicator"))
            .idPost(row.getLong("idPost"))
            .date(row.getString("date"))
            .text(row.getString("text"))
            .build();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Comment entity) {
        entityManager.persist(entity);
    }

    @Override
    public List<Comment> findAllCommentsOfPost(long id) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_POST_ID,new Object[]{id},commentRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }
}
