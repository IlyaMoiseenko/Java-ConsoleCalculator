package services;

import interfaces.UserStorage;
import interfaces.Validation;
import interfaces.Writer;
import storage.userStorage.JdbcUserStorage;
import io.ConsoleWriter;
import models.User;
import utils.Validator;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    //private final UserStorage userStorage = new FileUserStorage();
    private final UserStorage userStorage = new JdbcUserStorage();
    private final Validation validator = new Validator();
    private final Writer writer = new ConsoleWriter();

    public void create(String username, String password) {
        User newUser = new User(username, password);

        if (!validator.validate(newUser))
            writer.write("Invalid username or password");
        else {
            userStorage.add(newUser);
        }
    }

    public User getById(UUID id) {
        return userStorage.get(id);
    }

    public Optional<User> logIn(String username, String password) {
        return userStorage.get(username, password);
    }
}
