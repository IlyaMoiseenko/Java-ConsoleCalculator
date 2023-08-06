package services;

import interfaces.UserStorage;
import interfaces.impl.userStorage.FileUserStorage;
import models.User;

import java.util.Optional;
import java.util.UUID;

public class UserService {
    private final UserStorage userStorage = new FileUserStorage();

    public void create(String username, String password) {
        User newUser = new User(username, password);

        userStorage.add(newUser);
    }

    public Optional<User> getById(UUID id) {
        return userStorage.get(id);
    }

    public Optional<User> logIn(String username, String password) {
        return userStorage.get(username, password);
    }
}
