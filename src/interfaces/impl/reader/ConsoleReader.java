package interfaces.impl.reader;

import interfaces.Reader;

import java.util.Scanner;

/**
 * @author Simon Pirko on 25.07.23
 */
public class ConsoleReader implements Reader {

  private final Scanner scanner = new Scanner(System.in);

  @Override
  public double readNumber() {
    return scanner.nextDouble();
  }

  @Override
  public String readType() {
    return scanner.next();
  }
}
