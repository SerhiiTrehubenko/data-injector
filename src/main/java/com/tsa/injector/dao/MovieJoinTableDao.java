package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.TwoNumberHolder;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MovieJoinTableDao extends BaseDao<TwoNumberHolder> {
    public MovieJoinTableDao(DbConnector dbConnector, String joinTableName) {
        super(dbConnector);
        String plural = resolvePlural(joinTableName);
        insertQuery = "INSERT INTO movie_" + plural + " (movie_id, " + joinTableName + "_id) VALUES (?,?);";
        queryFindBy = "SELECT movie_id FROM movie_" + plural +
                " WHERE movie_id = ? AND " +
                 joinTableName + "_id = ?;";
    }

    private String resolvePlural(String joinTableName) {
        String substringPlural;
        if (joinTableName.endsWith("y") ) {
            substringPlural = joinTableName.substring(0, joinTableName.length()-1) + "ies";
        } else {
            substringPlural = joinTableName + "s";
        }
        return substringPlural;
    }

    @Override
    protected void fillInsert(PreparedStatement statement, TwoNumberHolder holder) throws SQLException {
        statement.setInt(1, holder.first());
        statement.setInt(2, holder.second());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, TwoNumberHolder holder) throws SQLException {
        statement.setInt(1, holder.first());
        statement.setInt(2, holder.second());
    }
}
