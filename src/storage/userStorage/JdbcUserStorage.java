package storage.userStorage;

import config.JdbcPostgresConfig;
import interfaces.UserStorage;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.UUID;

public class JdbcUserStorage implements UserStorage {
    private Connection connection;

    private final String ADD_USER = "insert into \"user\" values (?, ?, ?)";
    private final String GET_USER_BY_ID = "select * from \"user\" where id = ?";
    private final String GET_USER_BY_USERNAME_AND_PASSWORD = "select * from \"user\" where name = ? and password = ?";

    public JdbcUserStorage() {
        try {
            connection = JdbcPostgresConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void add(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_USER);
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User get(UUID id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_ID);
            preparedStatement.setString(1, id.toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID resultSetId = UUID.fromString(resultSet.getString(1));
                String resultSetUsername = resultSet.getString(2);
                String resultSetPassword = resultSet.getString(3);

                preparedStatement.close();
                return new User(resultSetId, resultSetUsername, resultSetPassword);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Optional<User> get(String username, String password) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_USER_BY_USERNAME_AND_PASSWORD);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                UUID resultSetId = UUID.fromString(resultSet.getString(1));
                String resultSetUsername = resultSet.getString(2);
                String resultSetPassword = resultSet.getString(3);

                preparedStatement.close();
                return Optional.of(new User(resultSetId, resultSetUsername, resultSetPassword));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
