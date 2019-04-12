package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        String sql = "select * from userinfo where id = ?";
        Object[] params = new Object[]{id};
        return jdbcContext.get(sql, params);
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
//        StatementStrategy statementStrategy = new AddStatementStrategy(user);
//        return jdbcContext.jdbcContextForAdd(statementStrategy);
        String sql = "insert into userinfo(name, password) values (?, ?)";
        Object[] params = new Object[]{user.getName(), user.getPassword()};
        return jdbcContext.add(sql, params);
    }


    public void update(User user) throws SQLException {
//        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
//        jdbcContext.jdbcContextForUpdate(statementStrategy);
        String sql = "update userinfo set name = ?, password = ? where id = ?";
        Object[] params = new Object[]{user.getName(), user.getPassword(), user.getId()};
        jdbcContext.update(sql, params);
    }

    public void delete(Long id) throws SQLException {
//        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
//        jdbcContext.jdbcContextForUpdate(statementStrategy);

        String sql = "delete from userinfo where id = ?";
        Object[] params = new Object[]{id};
        jdbcContext.update(sql, params);
    }

}


















