package interfaces;

import models.Operation;

import java.text.ParseException;
import java.util.List;

/**
 * @author Simon Pirko on 25.07.23
 */
public interface OperationStorage {
  void save(Operation operation);
  List<Operation> findAll();
}
