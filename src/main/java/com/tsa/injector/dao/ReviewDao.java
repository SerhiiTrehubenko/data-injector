package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.ReviewDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReviewDao extends BaseDao<ReviewDto> {
    public ReviewDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO movie_reviews (movie_id, user_id, movie_comment) " +
                "VALUES (?,?,?);";
        queryFindBy = "SELECT movie_id FROM movie_reviews " +
                "WHERE movie_id = ? AND " +
                "user_id = ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, ReviewDto dto) throws SQLException {
        statement.setInt(1, dto.getMovieId());
        statement.setInt(2, dto.getUserId());
        statement.setString(3, dto.getComment());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, ReviewDto dto) throws SQLException {
        statement.setInt(1, dto.getMovieId());
        statement.setInt(2, dto.getUserId());
    }
}
