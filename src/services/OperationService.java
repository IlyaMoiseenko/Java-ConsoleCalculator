package services;

import interfaces.OperationStorage;
import interfaces.impl.operationStorage.FileOperationStorage;
import interfaces.impl.operationStorage.InMemoryOperationStorage;
import models.Operation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OperationService {

    //private final OperationStorage storage = new InMemoryOperationStorage();
    private final OperationStorage storage = new FileOperationStorage();

    public Operation calculate(Operation operation) {
        switch (operation.getType()) {
            case "sum":
                operation.setResult(operation.getNum1() + operation.getNum2());
                operation.setCreatedDate(LocalDate.now());
                storage.save(operation);
                return operation;
            case "sub":
                operation.setResult(operation.getNum1() - operation.getNum2());
                operation.setCreatedDate(LocalDate.now());
                storage.save(operation);
                return operation;
            case "mul":
                operation.setResult(operation.getNum1() * operation.getNum2());
                operation.setCreatedDate(LocalDate.now());
                storage.save(operation);
                return operation;
            case "div":
                operation.setResult(operation.getNum1() / operation.getNum2());
                operation.setCreatedDate(LocalDate.now());
                storage.save(operation);
                return operation;
        }
        throw new RuntimeException();
    }

    public List<String> getHistory() {
        List<Operation> all = storage.findAll();
        List<String> result = new ArrayList<>();
        for (Operation operation : all) {
            result.add("Result = " + operation.getNum1() + " " + operation.getNum2() + " " + operation.getResult() + " " + operation.getCreatedDate());
        }
        return result;
    }
}
