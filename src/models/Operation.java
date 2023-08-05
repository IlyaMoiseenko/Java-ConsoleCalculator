package models;

public class Operation {
    private double num1;
    private double num2;
    private String type;
    private double result;
    private User user;

    public Operation(double num1, double num2, String type, double result) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
    }

    public Operation(double num1, double num2, String type, User user) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.user = user;
    }

    public Operation(double num1, double num2, String type, double result, User user) {
        this.num1 = num1;
        this.num2 = num2;
        this.type = type;
        this.result = result;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "num1=" + num1 +
                ", num2=" + num2 +
                ", type='" + type + '\'' +
                ", result=" + result +
                ", user=" + user +
                '}';
    }
}
