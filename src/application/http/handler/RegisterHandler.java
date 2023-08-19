package application.http.handler;

import application.http.util.HttpQueryUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import services.UserService;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class RegisterHandler implements HttpHandler {
    private final UserService userService = new UserService();
    private final String successMessage = "Register done!";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();

        String username = HttpQueryUtil.getParameterValue("username", query);
        String password = HttpQueryUtil.getParameterValue("password", query);

        userService.create(username, password);

        OutputStream responseBody = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, successMessage.getBytes().length);
        responseBody.write(successMessage.getBytes());
        responseBody.close();
    }
}
