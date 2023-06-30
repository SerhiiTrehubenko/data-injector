package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CountryDao extends BaseDao<String> {
    public CountryDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO countries (country_name) VALUES (?);";
        queryFindBy = "SELECT country_id FROM countries " +
                "WHERE countries.country_name LIKE ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, String country) throws SQLException {
        statement.setString(1, country);
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, String country) throws SQLException {
        statement.setString(1, country);
    }
}
