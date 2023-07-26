package models;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

/**
 * @author Simon Pirko on 25.07.23
 */
public class Operation {
  double num1;
  double num2;
  String type;
  double result;
  LocalDate createdDate;

  public Operation(double num1, double num2, String type, LocalDate localDate) {
    this.num1 = num1;
    this.num2 = num2;
    this.type = type;
    this.createdDate = localDate;
  }

  public Operation(double num1, double num2, String type, double result) {
    this.num1 = num1;
    this.num2 = num2;
    this.type = type;
    this.result = result;
  }

  public double getNum1() {
    return num1;
  }

  public void setNum1(double num1) {
    this.num1 = num1;
  }

  public double getNum2() {
    return num2;
  }

  public void setNum2(double num2) {
    this.num2 = num2;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public double getResult() {
    return result;
  }

  public void setResult(double result) {
    this.result = result;
  }

  public LocalDate getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(LocalDate createdDate) {
    this.createdDate = createdDate;
  }

  @Override
  public String toString() {
    return "models.Operation{" +
        "num1=" + num1 +
        ", num2=" + num2 +
        ", type='" + type + '\'' +
        ", result=" + result +
        ", createdDate=" + createdDate +
        '}';
  }
}
