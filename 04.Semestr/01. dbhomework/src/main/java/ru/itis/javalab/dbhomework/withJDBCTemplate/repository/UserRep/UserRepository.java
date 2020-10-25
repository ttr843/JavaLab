package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.UserRep;

import ru.itis.javalab.dbhomework.model.User;
import ru.itis.javalab.dbhomework.withJDBCTemplate.repository.CrudRepositories;

import javax.sql.DataSource;
import java.util.List;

public interface UserRepository extends CrudRepositories<User, Integer> {

    public void setDataSource(DataSource dataSource);

    public List<User> findAllByOneSelect();
}
