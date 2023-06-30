package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.GenreDto;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GenreDao extends BaseDao<GenreDto> {

    public GenreDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO genres (genre_name) VALUES (?);";
        queryFindBy = "SELECT genre_id FROM genres " +
                "WHERE genres.genre_name LIKE ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, GenreDto genreDto) throws SQLException {
        statement.setString(1, genreDto.getName());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement preparedStatement, GenreDto genreDto) throws SQLException {
        preparedStatement.setString(1, genreDto.getName());
    }
}
