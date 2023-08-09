package interfaces;

import models.Operation;

import java.util.List;

public interface Parser {
    List<Operation> fromJson(String jsonString);
    String toJson(List<Operation> operations);
}
