package com.ggue;

import java.sql.*;

public class UserDao {
    public User get(Long id) throws ClassNotFoundException, SQLException {
        //DB 가 뭐야? mysql
        //어딨어? 알려주께..
        //드라이버 로드

        Connection connection = getConnection();
        //SQL 쿼리 만들고
        //PreparedStatement?  1)쿼리문 분석 2) 컴파일 3)실행 단계를 한번만 거치고 캐시에 담아서 재사용(DB 과부화가 그냥 statement보다 적겠지?)
        PreparedStatement preparedStatement =
                connection.prepareStatement("select * from userinfo where id = ?");
        preparedStatement.setLong(1, id);

        //쿼리 실행하고
        ResultSet resultSet = preparedStatement.executeQuery();
        //실행된 쿼리를 오브젝트에 매핑하고
        resultSet.next(); // next야 DB의 [0]번째가 아닌 [1]번째로 옮겨줘! 0번째는 컬럼(DB의 열)이라서? DB의 커서개념 공부하시길~
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setName(resultSet.getString("name"));
        user.setPassword(resultSet.getString("password"));
        //자원 해지하고
        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return user;
    }

    public Long add(User user) throws ClassNotFoundException, SQLException {
        Connection connection = getConnection();
        PreparedStatement preparedStatement =
                connection.prepareStatement("insert into userinfo(name, password) values (? ,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        preparedStatement.executeUpdate();

        preparedStatement = connection.prepareStatement("select last_insert_id()");
        ResultSet resultSet = preparedStatement.executeQuery();


        resultSet.next();
        Long id = resultSet.getLong(1);

        resultSet.close();
        preparedStatement.close();
        connection.close();
        //리턴
        return id;

    }

    private Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver"); //jdbc? lib폴더에 mysql-connector에 있음
        //커넥션 맺고 (TCP layer 하위에서 작업됨)

                //jdbc driver loading...
        return DriverManager.getConnection("jdbc:mysql://172.18.102.128/jeju?serverTimezone=UTC"
                        , "portal", "portaljejunu");

    }
}
