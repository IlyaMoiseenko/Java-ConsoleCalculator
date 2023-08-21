package application.http.handler;

import application.http.util.HttpQueryUtil;
import application.http.util.HttpResponseBodyUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import interfaces.Session;
import models.User;
import services.UserService;
import session.ConsoleSession;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

public class LoginHandler implements HttpHandler {
    private final UserService userService = new UserService();
    private final Session session;

    public LoginHandler(Session session) {
        this.session = session;
    }

    private final String successMessage = "Login done!";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();

        String username = HttpQueryUtil.getParameterValue("username", query);
        String password = HttpQueryUtil.getParameterValue("password", query);

        Optional<User> currentUser = userService.logIn(username, password);

        currentUser.ifPresent(session::addCurrentSessionUser);

        HttpResponseBodyUtil.setSuccessMessage(exchange, successMessage);
    }
}
