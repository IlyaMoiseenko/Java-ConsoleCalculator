package interfaces.impl.userStorage;

import interfaces.UserStorage;
import models.User;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class FileUserStorage implements UserStorage {
    private String path = "src/resources/users.txt";
    private final File file = new File(path);

    public FileUserStorage() {}
    public FileUserStorage(String path) {
        this.path = path;
    }

    @Override
    public void add(User user) {
        try (FileWriter fileWriter = new FileWriter(file, true)) {
            fileWriter.write(patternForWriteInFile(user));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<User> get(UUID id) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String stringUserData = scanner.nextLine();
                String[] splitedUserData = stringUserData.split(":");
                UUID userId = UUID.fromString(splitedUserData[0]);

                if (userId.equals(id)) {
                    User user = convertStringUserDataToUser(splitedUserData);

                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> get(String username, String password) {
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNext()) {
                String stringUserData = scanner.nextLine();
                String[] splitedUserData = stringUserData.split(":");

                if (splitedUserData[1].equals(username) && splitedUserData[2].equals(password)) {
                    User user = convertStringUserDataToUser(splitedUserData);

                    return Optional.of(user);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }

    private User convertStringUserDataToUser(String[] userData) {
        return new User(
                UUID.fromString(userData[0]),
                userData[1],
                userData[2]
        );
    }

    private String patternForWriteInFile(User user) {
        return user.getId() + ":" + user.getUsername() + ":" + user.getPassword() + "\n";
    }
}
