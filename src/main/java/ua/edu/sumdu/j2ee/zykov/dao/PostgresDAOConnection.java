package ua.edu.sumdu.j2ee.zykov.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;

@Repository
public class PostgresDAOConnection extends DAOConnection {
    private static final Logger logger = LoggerFactory.getLogger(PostgresDAOConnection.class);
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
                logger.info("Connection Successful!");
            }
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
        return connection;
    }

    @Override
    public void disconnect() {
        try {
            connection.close();
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            logger.info("Connection was closed!");
        } catch (SQLException e) {
            logger.warn(e.getMessage());
        }
    }
}
