package session;

import interfaces.Session;
import models.User;
import services.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleSession implements Session {
    private User user;
    private final String path = "src/session/currentSession.txt";
    private final File file = new File(path);
    private final UserService userService = new UserService();
    private int statusCode = 1;

    public ConsoleSession() {
        Optional<User> currentUser = setCurrentSessionUser();

        currentUser.ifPresent(value -> user = value);
    }

    public User getUser() {
        return user;
    }

    @Override
    public void setStatusCode(int newStatusCode) {
        statusCode = newStatusCode;
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

    private Optional<User> setCurrentSessionUser() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                UUID userId = UUID.fromString(scanner.nextLine());

                return userService.getById(userId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    public void addCurrentSessionUser(User user) {
        this.user = user;

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write(user.getId().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeCurrentSessionUser() {
        this.user = null;

        try (FileWriter fileWriter = new FileWriter(file, false)) {
            fileWriter.write("");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
