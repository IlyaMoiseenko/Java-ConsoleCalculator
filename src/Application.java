import interfaces.Reader;
import interfaces.Session;
import interfaces.Writer;
import interfaces.impl.reader.ConsoleReader;
import interfaces.impl.writer.ConsoleWriter;
import models.Operation;
import models.User;
import services.OperationService;
import services.UserService;
import session.ConsoleSession;
import utils.UserData;

import java.util.*;

public class Application {

    private final Reader reader = new ConsoleReader();
    private final Writer writer = new ConsoleWriter();
    private final OperationService operationService = new OperationService();
    private final Session session = new ConsoleSession();
    private final UserService userService = new UserService();

    public void start() {
        while (session.getStatusCode() == 1) {
            if (session.getUser() == null)
                guestActions();
            else
                userActions();
        }
    }

    private void showGuestMenu() {
        writer.write("Choose:");
        writer.write("1 - Sign Up");
        writer.write("2 - Log In");
        writer.write("3 - Exit");
    }

    private void showUserMenu() {
        writer.write("Choose:");
        writer.write("1 - Calculator");
        writer.write("2 - Show history");
        writer.write("3 - Logout");
        writer.write("4 - Exit");
    }

    private Map<String, String> getUserData() {
        Map<String, String> userData = new HashMap<>();

        writer.write("Enter username:");
        String username = reader.readLine();
        userData.put(UserData.USERNAME_KEY, username);

        writer.write("Enter Password");
        String password = reader.readLine();
        userData.put(UserData.PASSWORD_KEY, password);

        return userData;
    }

    private void startCalculate() {
        writer.write("Enter num 1");
        double num1 = reader.readNumber();

        writer.write("Enter num 2");
        double num2 = reader.readNumber();

        writer.write("Choose type: sum, sub, mul, div");
        String type = reader.readType();

        Operation operation = new Operation(num1, num2, type, session.getUser());
        Operation result = operationService.calculate(operation);
        writer.write("Result = " + result.getResult());
    }

    private void showHistory() {
        List<String> historyByUser = operationService.getHistoryByUser(session.getUser());

        for (String result : historyByUser) {
            writer.write(result);
        }
    }

    private void guestActions() {
        showGuestMenu();

        double answer = reader.readNumber();
        if (answer == 1) {
            Map<String, String> userData = getUserData();

            userService.create(
                    userData.get(UserData.USERNAME_KEY),
                    userData.get(UserData.PASSWORD_KEY)
            );
        } else if (answer == 2) {
            Map<String, String> userData = getUserData();

            Optional<User> currentUser = userService.logIn(
                    userData.get(UserData.USERNAME_KEY),
                    userData.get(UserData.PASSWORD_KEY)
            );

            currentUser.ifPresent(session::addCurrentSessionUser);
        } else if (answer == 3) {
            session.setStatusCode(0);
        }
    }

    private void userActions() {
        showUserMenu();

        double answer = reader.readNumber();
        if (answer == 1) {
            startCalculate();
        } else if (answer == 2) {
            showHistory();
        } else if (answer == 3) {
            session.removeCurrentSessionUser();
        } else if (answer == 4) {
            session.setStatusCode(0);
        }
    }
}
