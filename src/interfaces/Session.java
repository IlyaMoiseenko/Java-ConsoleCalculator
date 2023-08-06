package interfaces;

import models.User;

import java.util.Optional;

public interface Session {
    Optional<User> getUser();

    private void setCurrentSessionUser() {};

    void addCurrentSessionUser(User user);

    void removeCurrentSessionUser();

    void setStatusCode(int newStatusCode);

    int getStatusCode();
}
