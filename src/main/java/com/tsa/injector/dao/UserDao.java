package com.tsa.injector.dao;

import com.tsa.injector.database.DbConnector;
import com.tsa.injector.domain.UserDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

public class UserDao extends BaseDao<UserDto> {
    private final static int USER_FIRST_NAME = 0;
    private final static int USER_LAST_NAME = 1;

    public UserDao(DbConnector dbConnector) {
        super(dbConnector);
        insertQuery = "INSERT INTO users (user_firstname, user_lastname, user_email, user_nickname) " +
                "VALUES (?,?,?,?);";
        queryFindBy = "SELECT user_id FROM users " +
                "WHERE user_firstname LIKE ? AND " +
                "user_lastname LIKE ? AND " +
                "user_email LIKE ?;";
    }

    @Override
    protected void fillInsert(PreparedStatement statement, UserDto user) throws SQLException {
        statement.setString(1, user.getFirstName());
        statement.setString(2, user.getLastName());
        statement.setString(3, user.getEmail());
        statement.setString(4, user.getNickName());
        statement.executeUpdate();
    }

    @Override
    protected void fillPresent(PreparedStatement preparedStatement, UserDto user) throws SQLException {
        preparedStatement.setString(1, user.getFirstName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setString(3, user.getEmail());
    }

    @Override
    public int findIdBy(String... criteria) {
        int foundId = 0;
        String selectQuery = "SELECT user_id FROM users WHERE users.user_firstname LIKE ? AND user_lastname LIKE ?;";
        try (Connection connection = dbConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(selectQuery)) {
            statement.setString(1, criteria[USER_FIRST_NAME].trim());
            statement.setString(2, criteria[USER_LAST_NAME].trim());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    foundId = resultSet.getInt("user_id");
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (foundId == 0) {
            throw new RuntimeException("The User with criteria: [%s] is not found".formatted(Arrays.toString(criteria)));
        }
        return foundId;
    }
}
