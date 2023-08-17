package services;

import interfaces.OperationStorage;
import storage.operationStorage.JdbcOperationStorage;
import models.Operation;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class OperationService {

    //private final OperationStorage storage = new InMemoryOperationStorage();
    //private final OperationStorage storage = new FileOperationStorage();
    //private final OperationStorage storage = new JsonOperationStorage();
    private final OperationStorage storage = new JdbcOperationStorage();

    public Operation calculate(Operation operation) {
        switch (operation.getType()) {
            case "sum":
                operation.setResult(operation.getNum1() + operation.getNum2());
                storage.save(operation);
                return operation;
            case "sub":
                operation.setResult(operation.getNum1() - operation.getNum2());
                storage.save(operation);
                return operation;
            case "mul":
                operation.setResult(operation.getNum1() * operation.getNum2());
                storage.save(operation);
                return operation;
            case "div":
                operation.setResult(operation.getNum1() / operation.getNum2());
                storage.save(operation);
                return operation;
        }

        throw new RuntimeException();
    }

    public List<String> getHistory() {
        List<Operation> all = storage.findAll();
        List<String> result = new ArrayList<>();

        for (Operation operation : all) {
            result.add("Result = " + operation.getNum1() + " " + operation.getNum2() + " " + operation.getResult());
        }

        return result;
    }

    public List<String> getHistoryByUser(User user) {
        List<Operation> allByUser = storage.findAllByUser(user);
        List<String> result = new ArrayList<>();

        if (allByUser.isEmpty()) {
            result.add("The story is empty");
            return result;
        }

        for (Operation operation : allByUser) {
            result.add(operation.getNum1() + operation.getType() + operation.getNum2() + " = " + operation.getResult());
        }

        return result;
    }
}
