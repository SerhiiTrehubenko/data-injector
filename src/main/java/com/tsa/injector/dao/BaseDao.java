package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class BaseDao<T> {
    protected String insertQuery;
    protected String queryFindBy;

    protected final DbConnector dbConnector;

    protected BaseDao(DbConnector dbConnector) {
        this.dbConnector = dbConnector;
    }


    public void insert(T dto) {
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            fillInsert(statement, dto);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void fillInsert(PreparedStatement statement, T dto) throws SQLException;

    public boolean notPresent(T dto) {
        return getId(dto) == 0;
    }
    public int getId(T dto) {
        int foundId = 0;
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(queryFindBy)) {
            fillPresent(preparedStatement, dto);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    foundId = resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw  new RuntimeException(e);
        }
        return foundId;
    }

    protected abstract void fillPresent(PreparedStatement statement, T dto) throws SQLException;

    public void deleteBy(T dto) {
        throw new UnsupportedOperationException("Method [deleteBy(T dto)] is not implemented in Class: [%s]".formatted(getClass().getName()));
    }

    public int findIdBy(String... criteria) {
        throw new UnsupportedOperationException("Method [deleteBy(T dto)] is not implemented in Class: [%s]".formatted(getClass().getName()));
    }
}
