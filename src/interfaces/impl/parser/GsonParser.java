package interfaces.impl.parser;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import interfaces.Parser;
import models.Operation;

import java.lang.reflect.Type;
import java.util.List;

public class GsonParser implements Parser {
    private final Gson gson = new Gson();
    private final Type jsonListTypeToken = new TypeToken<List<Operation>>(){}.getType();

    @Override
    public List<Operation> fromJson(String jsonString) {
        return gson.fromJson(jsonString, jsonListTypeToken);
    }

    @Override
    public String toJson(List<Operation> operations) {
        return gson.toJson(operations);
    }
}
