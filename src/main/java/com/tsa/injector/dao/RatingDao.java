package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.RatingDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RatingDao extends BaseDao<RatingDto> {
    public RatingDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO movie_ratings (movie_id, user_id, movie_raring) " +
                "VALUES (?,?,?);";
        queryFindBy = "SELECT movie_id FROM movie_ratings " +
                "WHERE movie_id = ? AND " +
                "user_id = ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, RatingDto rating) throws SQLException {
        statement.setInt(1, rating.movieId());
        statement.setInt(2, rating.userId());
        statement.setDouble(3, rating.rating());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, RatingDto rating) throws SQLException {
        statement.setInt(1, rating.movieId());
        statement.setInt(2, rating.userId());
    }
}
