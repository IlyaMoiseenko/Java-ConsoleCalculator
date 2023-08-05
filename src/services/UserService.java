package services;

import interfaces.UserStorage;
import interfaces.impl.userStorage.FileUserStorage;
import models.User;

import java.util.UUID;

public class UserService {
    private UserStorage userStorage = new FileUserStorage();

    public void create(String username, String password) {
        User newUser = new User(username, password);

        userStorage.add(newUser);
    }

    public User getById(UUID id) {
        return userStorage.get(id);
    }

    public User logIn(String username, String password) {
        User user = userStorage.get(username, password);

        return user;
    }
}
