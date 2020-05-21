package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PostgresDAOConnection extends DAOConnection {
    private final DataSource dataSource;

    public PostgresDAOConnection(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public PreparedStatement getStatement() {
        return statement;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    @Override
    public Connection connect() {
        try {
            connection = dataSource.getConnection();
            if (!connection.isClosed()) {
                System.out.println("Connection Successful!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
            resultSet.close();
            statement.close();
            System.out.println("Connection was closed!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
