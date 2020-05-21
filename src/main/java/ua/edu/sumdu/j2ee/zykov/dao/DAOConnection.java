package ua.edu.sumdu.j2ee.zykov.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public abstract class DAOConnection {
    protected Connection connection;
    protected Statement statement;
    protected ResultSet resultSet;

    public Statement getStatement() {
        return statement;
    }

    public ResultSet getResultSet() {
        return resultSet;
    }

    public void setStatement(Statement statement) {
        this.statement = statement;
    }

    public void setResultSet(ResultSet resultSet) {
        this.resultSet = resultSet;
    }

    abstract Connection connect();
    abstract void disconnect();
}
