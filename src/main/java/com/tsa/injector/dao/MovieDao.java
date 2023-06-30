package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.MovieDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class MovieDao extends BaseDao<MovieDto> {
    private final static int MOVIE_RUS_NAME = 0;
    public MovieDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO movies (movie_rus_name, movie_en_name, movie_release_year, movie_description, movie_price) " +
                "VALUES (?,?,?,?,?);";
        queryFindBy = "SELECT movie_id FROM movies " +
                "WHERE movie_rus_name LIKE ? AND " +
                "movie_en_name LIKE ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, MovieDto movie) throws SQLException {
        statement.setString(1, movie.getRusName());
        statement.setString(2, movie.getEngName());
        statement.setInt(3, movie.getReleaseYear());
        statement.setString(4, movie.getDescription());
        statement.setDouble(5, movie.getPrice());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement statement, MovieDto movie) throws SQLException {
        statement.setString(1, movie.getRusName());
        statement.setString(2, movie.getEngName());
    }

    @Override
    public void deleteBy(MovieDto dto) {
        String deleteQuery = "DELETE FROM movies WHERE movie_id = ?;";
        try (Connection connection = dbConnector.getConnection();
        PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
            statement.setInt(1, getId(dto));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findIdBy(String... criteria) {
        int foundId = 0;
        String selectQuery = "SELECT movie_id FROM movies WHERE movie_rus_name LIKE ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, criteria[MOVIE_RUS_NAME].trim());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    foundId = resultSet.getInt("movie_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (foundId == 0) {
            throw new RuntimeException("The Movie with criterion: [%s] is not found".formatted(Arrays.toString(criteria)));
        }
        return foundId;
    }
}
