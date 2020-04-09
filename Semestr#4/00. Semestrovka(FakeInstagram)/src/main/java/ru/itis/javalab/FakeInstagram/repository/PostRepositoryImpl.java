package ru.itis.javalab.FakeInstagram.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.Role;
import ru.itis.javalab.FakeInstagram.model.State;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.service.UploadService;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public class PostRepositoryImpl implements PostRepository {


    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Post> postRowMapper = (row, rowNumber) ->
            Post.builder()
                    .id(row.getLong("id"))
                    .idPublicator(row.getLong("idpublicator"))
                    .text(row.getString("text"))
                    .likeOfPost(row.getLong("likeofpost"))
                    .date(row.getString("date"))
                    .place(row.getString("place"))
                    .photo(row.getString("photo"))
                    .build();

    private static final String SQL_SAVE = "INSERT  INTO posts( idpublicator, text, date, place, photo) " +
            "VALUES(?,?,?,?,?)";
    @Override
    public void save(Post post) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, post.getIdPublicator());
            statement.setString(2, post.getText());
            Date date = new Date(System.currentTimeMillis());
            statement.setString(3, date.toString() );
            statement.setString(4,post.getPlace());
            statement.setString(5, post.getPhoto() );

            return statement;
        });

    }

    private static final String SQL_FIND_BY_USER_ID = "SELECT * from posts where idpublicator = ?";
    @Override
    public List<Post> findPostsByUserId(Long id) {
        try {
            List<Post> posts = jdbcTemplate.query(SQL_FIND_BY_USER_ID,new Object[]{id},postRowMapper);
            return posts;
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }

    private static final String SQL_FIND_BY_ID = "SELECT * from posts where id = ?";
    @Override
    public Post findPostById(Long postId) {
        try {
            Post post = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{postId}, postRowMapper);
            return post;
        }catch (EmptyResultDataAccessException e) {
            throw  new EmptyResultDataAccessException(0);
        }
    }


}
