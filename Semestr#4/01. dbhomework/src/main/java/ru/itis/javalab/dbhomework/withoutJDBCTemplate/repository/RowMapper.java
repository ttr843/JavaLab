package ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface RowMapper<T> {
    T mapRow(ResultSet row) throws SQLException;
}
