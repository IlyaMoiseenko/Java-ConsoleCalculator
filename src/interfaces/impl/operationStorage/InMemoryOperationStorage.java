package interfaces.impl.operationStorage;

import interfaces.OperationStorage;
import models.Operation;
import models.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryOperationStorage implements OperationStorage {

    private final List<Operation> operations = new ArrayList<>();

    @Override
    public void save(Operation operation) {
        operations.add(operation);
    }

    @Override
    public List<Operation> findAll() {
        return new ArrayList<>(this.operations);
    }

    @Override
    public List<Operation> findAllByUser(User user) {
        return null;
    }
}
