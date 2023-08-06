package interfaces;

import models.User;

public interface Session {
    User getUser();

    private void setCurrentSessionUser() {};

    void addCurrentSessionUser(User user);

    void removeCurrentSessionUser();

    void setStatusCode(int newStatusCode);

    int getStatusCode();
}
