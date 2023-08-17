package storage.operationStorage;

import config.JdbcPostgresConfig;
import interfaces.OperationStorage;
import models.Operation;
import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JdbcOperationStorage implements OperationStorage {
    private Connection connection;

    private final String ADD_OPERATION = "insert into \"operation\" values (?, ?, ?, ?, ?)";
    private final String GET_ALL_OPERATIONS = "select * from \"operation\"";
    private final String GET_ALL_OPERATIONS_BY_USER = "select * from \"operation\" where user_id = ?";
    public JdbcOperationStorage() {
        try {
            connection = JdbcPostgresConfig.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void save(Operation operation) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_OPERATION);
            preparedStatement.setDouble(1, operation.getNum1());
            preparedStatement.setDouble(2, operation.getNum2());
            preparedStatement.setString(3, operation.getType());
            preparedStatement.setDouble(4, operation.getResult());
            preparedStatement.setString(5, operation.getUserId().toString());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Operation> findAll() {
        List<Operation> allOperations = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_OPERATIONS);

            while (resultSet.next()) {
                double resultSetNum1 = resultSet.getDouble(1);
                double resultSetNum2 = resultSet.getDouble(2);
                String resultSetOperationType = resultSet.getString(3);
                double resultSetOperationResult = resultSet.getDouble(4);
                UUID resultSetUserId = UUID.fromString(resultSet.getString(5));

                Operation operation = new Operation(
                        resultSetNum1,
                        resultSetNum2,
                        resultSetOperationType,
                        resultSetOperationResult,
                        resultSetUserId
                );

                allOperations.add(operation);
            }

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return allOperations;
    }

    @Override
    public List<Operation> findAllByUser(User user) {
        List<Operation> operationsByUser = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_OPERATIONS_BY_USER);
            preparedStatement.setString(1, user.getId().toString());

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double resultSetNum1 = resultSet.getDouble(1);
                double resultSetNum2 = resultSet.getDouble(2);
                String resultSetOperationType = resultSet.getString(3);
                double resultSetOperationResult = resultSet.getDouble(4);
                UUID resultSetUserId = UUID.fromString(resultSet.getString(5));

                Operation operation = new Operation(
                        resultSetNum1,
                        resultSetNum2,
                        resultSetOperationType,
                        resultSetOperationResult,
                        resultSetUserId
                );

                operationsByUser.add(operation);
            }

            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operationsByUser;
    }
}
