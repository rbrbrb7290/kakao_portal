package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import java.sql.*;

public class UserDao {
    private final DataSource datasource;

    public UserDao(DataSource datasource)
    {
        this.datasource = datasource;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user;
        try {
            connection = datasource.getConnection();
            // 쿼리만들고
            preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
            preparedStatement.setLong(1, id);
            // 실행
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            // 결과매핑
            user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName(resultSet.getString("name"));
            user.setPassword(resultSet.getString("password"));
        } finally {
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return user;
    }


    public Long add(User user) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        Long id = null;
        try {
            connection = datasource.getConnection();
            // 쿼리만들고
            preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?,?)");
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            // 실행

            id = getLastInsertId(connection);
        } finally {
            if(preparedStatement != null)
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            if(connection != null)
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }


        return id;
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
            if(resultSet != null)
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
        }

        return id;
    }

}