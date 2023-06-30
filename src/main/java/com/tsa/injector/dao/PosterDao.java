package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.PosterDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PosterDao extends BaseDao<PosterDto> {
    public PosterDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO posters (movie_id, poster_link) VALUES (?, ?)";
        queryFindBy = "SELECT movie_id FROM posters " +
                "WHERE movie_id = ? AND " +
                "poster_link LIKE ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, PosterDto dto) throws SQLException {
        statement.setInt(1, dto.getMovieId());
        statement.setString(2, dto.getPosterLink());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, PosterDto dto) throws SQLException {
        statement.setInt(1, dto.getMovieId());
        statement.setString(2, dto.getPosterLink());
    }
}
