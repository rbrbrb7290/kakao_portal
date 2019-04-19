package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStatemnetStrategy implements StatementStrategy {
    private User user;
    public AddStatemnetStrategy(User user) {
        this.user = user;
    }

    @Override
    public PreparedStatement MakePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("insert into userinfo (name, password) values (?,?)");
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getPassword());

        return preparedStatement;
    }
}
