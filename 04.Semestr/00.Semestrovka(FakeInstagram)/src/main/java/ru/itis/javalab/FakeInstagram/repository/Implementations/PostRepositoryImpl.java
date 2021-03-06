package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.repository.interfaces.PostRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private RowMapper<Long> idPostRowMapper = (row,rowNumber) ->
            row.getLong("idpost");

    private static final String SQL_SAVE = "INSERT  INTO posts( idpublicator, text, date, place, photo) " +
            "VALUES(?,?,?,?,?)";

    private static final String SQL_ADD_POST_TO_FAVORITES = "INSERT INTO favorites(idowner, idpost) VALUES(?,?)";

    private static final String SQL_FIND_FAVORITES_POST_ID = "select * from favorites where idowner = ?";

    @Override
    public void save(Post post) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, post.getIdPublicator());
            statement.setString(2, post.getText());
            Date date = new Date(System.currentTimeMillis());
            statement.setString(3, date.toString());
            statement.setString(4, post.getPlace());
            statement.setString(5, post.getPhoto());

            return statement;
        });

    }

    private static final String SQL_FIND_BY_USER_ID = "SELECT * from posts where idpublicator = ?";

    @Override
    public List<Post> findPostsByUserId(Long id) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_USER_ID, new Object[]{id}, postRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }

    private static final String SQL_FIND_BY_ID = "SELECT * from posts where id = ?";

    @Override
    public Post findPostById(Long postId) {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{postId}, postRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }

    private static final String SQL_FIND_BY_HASH_TAG = "SELECT * from posts where text like ?";

    @Override
    public List<Post> findPostsByHashTag(String hashtag) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_HASH_TAG, new Object[]{"%" + hashtag + "%"}, postRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }

    @Override
    public void addPostToFavorites(long idPost, long idUser) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_ADD_POST_TO_FAVORITES,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, idUser);
            statement.setLong(2, idPost);
            return statement;
        });
    }

    @Override
    public List<Post> findAllFavoritesPosts(Long id) {
        try {
            List<Long> idPosts = jdbcTemplate.query(SQL_FIND_FAVORITES_POST_ID, new Object[]{id}, idPostRowMapper);
            List<Post> posts = new ArrayList<>();
            for (Long x: idPosts
                 ) {
                posts.add(findPostById(x));
            }
            return posts;
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }


}
