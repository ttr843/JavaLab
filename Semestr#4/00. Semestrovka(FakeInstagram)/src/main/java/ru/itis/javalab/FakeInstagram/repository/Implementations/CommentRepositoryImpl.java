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
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {
    private static final String SQL_FIND_BY_POST_ID = "SELECT * FROM comments where idPost = ?";
    private static final String SQL_SAVE = "INSERT INTO comments(publicatorname,idpost,likeofcomment,text,date) VALUES(?,?,?,?,?)";

    private RowMapper<Comment> commentRowMapper = (row,rowNumber) ->
            Comment.builder()
            .id(row.getLong("id"))
            .namePublicator(row.getString("publicatorname"))
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

    @Override
    public void saveWithJdbcTemplate(Comment comment) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, comment.getNamePublicator());
            statement.setLong(2, comment.getIdPost());
            statement.setLong(3, 0 );
            statement.setString(4, comment.getText());
            statement.setString(5, comment.getDate());
            return statement;
        });
    }
}
