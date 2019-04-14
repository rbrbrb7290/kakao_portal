package kr.ac.jejunu.userdao;

import java.sql.*;

public class UserDao {
    private final ConnectionMaker connectionMaker;

//    //UserDao를 호출하는 녀석에게 의존성을 던짐 who? DaoTest -> DaoFactory
    public UserDao(ConnectionMaker connectionMaker) {
        this.connectionMaker = connectionMaker;
    }

    public User get(Long id) throws ClassNotFoundException, SQLException {
        Connection connection = connectionMaker.getConnection();
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

    public Long add(User user) throws SQLException, ClassNotFoundException {
        //데이터는어디에?   Mysql
        //Driver Class Load
        Connection connection = connectionMaker.getConnection();
        // 쿼리만들고
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate(); //Update는 정수형 반환(select제외) ,execute - boolean , executeQuery - 객체값(select구문)

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
