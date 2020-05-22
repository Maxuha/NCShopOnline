package ua.edu.sumdu.j2ee.zykov.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAOConnection {
    protected Connection connection;
    protected PreparedStatement statement;
    protected ResultSet resultSet;

    public PreparedStatement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setStatement(PreparedStatement statement) {
        this.statement = statement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    abstract Connection connect();
    abstract void disconnect();
}
