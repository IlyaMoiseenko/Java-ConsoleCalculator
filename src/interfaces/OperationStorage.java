package interfaces;

import models.Operation;
import models.User;

import java.util.List;

public interface OperationStorage {
    void save(Operation operation);

    List<Operation> findAll();

    List<Operation> findAllByUser(User user);
}
