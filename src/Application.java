import interfaces.Reader;
import interfaces.Writer;
import interfaces.impl.reader.ConsoleReader;
import interfaces.impl.writer.ConsoleWriter;
import models.Operation;
import services.OperationService;

import java.time.LocalDate;
import java.util.List;

/**
 * @author Simon Pirko on 25.07.23
 */
public class Application {

  private final Reader reader = new ConsoleReader();
  private final Writer writer = new ConsoleWriter();
  private final OperationService operationService = new OperationService();

  public void start() {
    while (true) {
      writer.write("Enter num 1");
      double num1 = reader.readNumber();
      writer.write("Enter num 2");
      double num2 = reader.readNumber();
      writer.write("Choose type: sum, sub, mul, div");
      String type = reader.readType();
      Operation operation = new Operation(num1, num2, type, LocalDate.now());
      Operation result = operationService.calculate(operation);
      writer.write("Result = " + result.getResult());

      writer.write("Continue? 1 - yes, 2 - no, 3 - show history");
      double answer = reader.readNumber();
      if (answer == 2) {
        break;
      }
      if (answer == 3) {
        List<String> history = operationService.getHistory();
        for (String op : history) {
          writer.write(op);
        }
      }
    }
  }
}
