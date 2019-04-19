package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext {
    final DataSource dataSource;

    public JdbcContext(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User JdbcContextForGet(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = dataSource.getConnection();
            // 쿼리만들고
            preparedStatement = statementStrategy.MakePreparedStatement(connection);
//            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
//            preparedStatement.setLong(1, id);
            // 실행
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // 결과매핑
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setPassword(resultSet.getString("password"));
            }
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        //자원을 해지한다.
        return user;
    }

    public Long JdbcContextForAdd(StatementStrategy statementStrategy) throws SQLException {
        Long id;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();
            preparedStatement = statementStrategy.MakePreparedStatement(connection);

            preparedStatement.executeUpdate();
            // 실행
            id = getLastInsertId(connection);
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

    public void JdbcContextForUpdate(StatementStrategy statementStrategy) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = dataSource.getConnection();

            preparedStatement = statementStrategy.MakePreparedStatement(connection);
//            preparedStatement = connection.prepareStatement("update userinfo set name = ? , password = ? where id = ?");
//            preparedStatement.setString(1, user.getName());
//            preparedStatement.setString(2, user.getPassword());
//            preparedStatement.setLong(3, user.getId());
            preparedStatement.executeUpdate();
        } finally {
            if (preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if (connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
    }

    public Long getLastInsertId(Connection connection) throws SQLException {
        ResultSet resultSet = null;
        Long id = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select last_insert_id()");
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            id = resultSet.getLong(1);
        } finally {
            if (resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }
        return id;
    }
}