package models;

import java.util.UUID;

public class Operation {
    private double num1;
    private double num2;
    private String type;
    private double result;
    private UUID userId;

    public Operation(double num1, double num2, String type, double result) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
    }

    public Operation(double num1, double num2, String type, UUID userId) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.userId = userId;
    }

    public Operation(double num1, double num2, String type, double result, UUID userId) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
        this.userId = userId;
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

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", type='" + type + '\'' +
                ", result=" + result +
                ", userId=" + userId +
                '}';
    }
}
