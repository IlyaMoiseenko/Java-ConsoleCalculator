package interfaces.impl.operationStorage;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import interfaces.OperationStorage;
import models.Operation;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonOperationStorage implements OperationStorage {
    private String path = "src/resources/history.json";
    private final Gson gson = new Gson();
    private final Type jsonListTypeToken = new TypeToken<List<Operation>>(){}.getType();

    public JsonOperationStorage() {}

    public JsonOperationStorage(String path) {
        this.path = path;
    }

    @Override
    public void save(Operation operation) {
        List<Operation> all = findAll();
        if (all == null)
            all = new ArrayList<>();

        all.add(operation);

        String jsonData = gson.toJson(all);

        try (FileWriter fileWriter = new FileWriter(path, false)) {
            fileWriter.write(jsonData);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Operation> findAll() {
        String jsonOperationData;

        try {
            jsonOperationData = Files.readString(Path.of(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return gson.fromJson(jsonOperationData, jsonListTypeToken);
    }

    @Override
    public List<Operation> findAllByUser(User user) {
        List<Operation> all = findAll();

        return all.stream().filter(operation -> operation.getUserId().equals(user.getId())).toList();
    }
}
