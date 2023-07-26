package interfaces.impl.writer;

import interfaces.Writer;

/**
 * @author Simon Pirko on 25.07.23
 */
public class ConsoleWriter implements Writer {
  @Override
  public void write(String message) {
    System.out.println(message);
  }
}
