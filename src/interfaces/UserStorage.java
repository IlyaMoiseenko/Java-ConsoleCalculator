package interfaces;

import models.User;

import java.util.UUID;

public interface UserStorage {
    void add(User user);

    User get(UUID id);

    User get(String username, String password);
}
