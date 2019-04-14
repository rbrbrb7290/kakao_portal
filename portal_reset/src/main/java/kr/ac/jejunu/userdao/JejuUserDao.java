package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JejuUserDao extends UserDao {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Connection    접속정보는? localhost jeju id : jeju pw: jejupw
            return DriverManager.getConnection("jdbc:mysql://localhost/jeju?serverTimezone=UTC", "root", "");

    }
}