package interfaces;

import models.User;

import java.util.Optional;
import java.util.UUID;

public interface UserStorage {
    void add(User user);

    Optional<User> get(UUID id);

    Optional<User> get(String username, String password);
}
