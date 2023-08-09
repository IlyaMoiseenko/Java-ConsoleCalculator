package interfaces.impl.operationStorage;

import interfaces.OperationStorage;
import interfaces.Parser;
import interfaces.impl.parser.GsonParser;
import models.Operation;
import models.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class JsonOperationStorage implements OperationStorage {
    private String path = "src/resources/history.json";
    private final Parser parser = new GsonParser();

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

        String jsonData = parser.toJson(all);

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

        return parser.fromJson(jsonOperationData);
    }

    @Override
    public List<Operation> findAllByUser(User user) {
        List<Operation> all = findAll();

        return all.stream().filter(operation -> operation.getUser().getId().equals(user.getId())).toList();
    }
}
