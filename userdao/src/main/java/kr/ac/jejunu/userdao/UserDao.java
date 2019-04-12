package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final JdbcContext jdbcContext;

    public UserDao(JdbcContext jdbcContext) {
        this.jdbcContext = jdbcContext;
    }

    public User get(Long id) throws SQLException {
        StatementStrategy statementStrategy = new StatementStrategy() {
            @Override
            public PreparedStatement makePrepareStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
                preparedStatement.setLong(1, id);
                return preparedStatement;
            }
        };
        return jdbcContext.jdbcContextForGet(statementStrategy);
        //템플릿 콜백 패턴 : 실행되는 메소드 내용자체를 파라미터로 넘겨주려할때 java 1.8에서 람다를 써서 간단하게 변형가능 (메소드 하나일경우)
    }


    public Long add(User user) throws ClassNotFoundException, SQLException {
        StatementStrategy statementStrategy = new AddStatementStrategy(user);
        return jdbcContext.jdbcContextForAdd(statementStrategy);
    }


    public void update(User user) throws SQLException {
        StatementStrategy statementStrategy = new UpdateStatementStrategy(user);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

    public void delete(Long id) throws SQLException {
        StatementStrategy statementStrategy = new DeleteStatementStrategy(id);
        jdbcContext.jdbcContextForUpdate(statementStrategy);
    }

}


















