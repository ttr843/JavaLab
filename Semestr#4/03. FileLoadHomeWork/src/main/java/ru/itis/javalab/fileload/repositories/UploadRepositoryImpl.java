package ru.itis.javalab.fileload.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.fileload.models.Upload;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UploadRepositoryImpl implements UploadRepository {

    private static final String SQL_FIND_ALL = "SELECT  * from files";
    private static final String SQL_SAVE = "Insert into files(original_name, generated_name, size, type, path) " +
            "values(?,?,?,?,?)";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM files where id=?";
    private static final String SQL_FIND_BY_GENERATED_NAME = "SELECT * FROM files where generated_name=?";
    private static final String SQL_FIND_BY_ORIGINAL_NAME = "SELECT * FROM files where original_name=?";

    @Autowired
    private JdbcTemplate jdbcTemplate;


    private RowMapper<Upload> fileRowMapper = (row, rowNumber) ->
            Upload.builder()
                    .id(Long.valueOf(row.getString("id")))
                    .originalName(row.getString("original_name"))
                    .generatedName(row.getString("generated_name"))
                    .path(row.getString("path"))
                    .size(Double.parseDouble(row.getString("size")))
                    .type(row.getString("type"))
                    .build();

    @Override
    public Optional<Upload> find(Long id) {
        try {
            Upload upload = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, fileRowMapper);
            return Optional.ofNullable(upload);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Upload> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL, fileRowMapper);
    }

    @Override
    public void save(Upload entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(SQL_SAVE);
            statement.setString(1, entity.getOriginalName());
            statement.setString(2, entity.getGeneratedName());
            statement.setDouble(3, entity.getSize());
            statement.setString(4, entity.getType());
            statement.setString(5, entity.getPath());
            return statement;
        });
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Optional<Upload> find(String name) {
        try {
            Upload upload = jdbcTemplate.queryForObject(SQL_FIND_BY_GENERATED_NAME, new Object[]{name}, fileRowMapper);
            return Optional.ofNullable(upload);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Upload> findByOriginalName(String name) {
        try {
            Upload upload = jdbcTemplate.queryForObject(SQL_FIND_BY_ORIGINAL_NAME, new Object[]{name}, fileRowMapper);
            return Optional.ofNullable(upload);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
