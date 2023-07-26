package interfaces.impl.operationStorage;

import interfaces.OperationStorage;
import models.Operation;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Simon Pirko on 25.07.23
 */
public class FileOperationStorage implements OperationStorage {
  private String path = "src/resources/history.txt";
  private File file = new File(path);

  public FileOperationStorage () {}
  public FileOperationStorage(String path) {
    this.path = path;
  }

  @Override
  public void save(Operation operation) {
    try (FileWriter fileWriter = new FileWriter(file, true)) {
      fileWriter.write(
              operation.getNum1() + " " + operation.getType() + " " + operation.getNum2() + " = " + operation.getResult() + " " + operation.getCreatedDate() + "\n"
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

  private Operation convertStringToOperation(String operation) {
    String[] operands = operation.split(" ");

    return new Operation(
            Double.parseDouble(operands[0]),
            Double.parseDouble(operands[2]),
            operands[1],
            LocalDate.parse(operands[5])
            );
  }
}
