package kr.ac.jejunu.userdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeletStatementStrategy implements StatementStrategy {
    private Long id;

    public DeletStatementStrategy(Long id)
    {
        this.id = id;
    }


    @Override
    public PreparedStatement MakePreparedStatement(Connection connection) throws SQLException {
        PreparedStatement preparedStatement = null;
        preparedStatement = connection.prepareStatement("delete from userinfo where id = ?");
        preparedStatement.setLong(1, id);
        return preparedStatement;
    }
}
