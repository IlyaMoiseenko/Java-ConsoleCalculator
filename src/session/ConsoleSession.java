package session;

import interfaces.Session;
import models.User;
import services.UserService;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.UUID;

public class ConsoleSession implements Session {
    private User user;
    private final String path = "src/session/currentSession.txt";
    private final File file = new File(path);
    private final UserService userService = new UserService();

    public ConsoleSession() {
        user = setCurrentSessionUser();
    }

    public User getUser() {
        return user;
    }

    private User setCurrentSessionUser() {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                UUID userId = UUID.fromString(scanner.nextLine());

                return userService.getById(userId);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
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
