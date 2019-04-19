package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        StatementStrategy statementStrategy = new GetStatementStrategy(id);
        return jdbcContext.JdbcContextForGet(statementStrategy);
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatemnetStrategy(user);
        Long id = null;
        // 쿼리만들고
        return jdbcContext.JdbcContextForAdd(statementStrategy);
    }

    public void Update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.JdbcContextForUpdate(statementStrategy);

    }


    public void Delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeletStatementStrategy(id);
        // 쿼리만들고
        jdbcContext.JdbcContextForUpdate(statementStrategy);
    }

}