package kr.ac.jejunu.userdao;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;

public class UserDao {
    private final DataSource datasource;

    public UserDao(DataSource datasource){
        this.datasource = datasource;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        Connection connection = datasource.getConnection();
        // 쿼리만들고
        PreparedStatement preparedStatement = connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        // 실행
        ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        // 결과매핑
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));

        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = datasource.getConnection();
        // 쿼리만들고
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name,password) values (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());
        preparedStatement.executeUpdate();


        // 실행
        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();


        resultSet.next();
        Long id = resultSet.getLong(1);



        //자원을 해지한다.
        resultSet.close();
        preparedStatement.close();
        connection.close();

        return id;
    }

}

