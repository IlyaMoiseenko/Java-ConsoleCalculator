package storage.operationStorage;

import interfaces.OperationStorage;
import models.Operation;
import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class FileOperationStorage implements OperationStorage {
    private String path = "src/resources/history.txt";
    private File file = new File(path);

    public FileOperationStorage() {
    }

    public FileOperationStorage(String path) {
        this.path = path;
    }

    @Override
    public void save(Operation operation) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(
                    operation.getUserId() + ":"
                    + operation.getNum1() + ":"
                    + operation.getType() + ":"
                    + operation.getNum2() + ":"
                    + operation.getResult() + "\n"
            );
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Operation> findAll() {
        List<Operation> operations = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                Operation operationFromFile = convertStringToOperation(scanner.nextLine());
                operations.add(operationFromFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return operations;
    }

    @Override
    public List<Operation> findAllByUser(User user) {
        List<Operation> operations = new ArrayList<>();

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                String[] splitedData = data.split(":");

                if (UUID.fromString(splitedData[0]).equals(user.getId())) {
                    Operation operationFromFile = convertStringToOperation(data);
                    operationFromFile.setUserId(user.getId());
                    operations.add(operationFromFile);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return operations;
    }

    private Operation convertStringToOperation(String operation) {
        String[] operands = operation.split(":");

        return new Operation(
                Double.parseDouble(operands[1]),
                Double.parseDouble(operands[3]),
                operands[2],
                Double.parseDouble(operands[4])
        );
    }
}
