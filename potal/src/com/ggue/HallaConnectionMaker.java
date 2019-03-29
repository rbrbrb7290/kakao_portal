package com.ggue;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HallaConnectionMaker implements ConnectionMaker {
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://172.18.102.128/halla?serverTimezone=UTC"
                , "portal", "portaljejunu");
    }
}
