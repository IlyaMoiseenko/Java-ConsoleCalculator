package application.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import interfaces.Session;
import session.ConsoleSession;

import java.io.IOException;
import java.io.OutputStream;

public class LogoutHandler implements HttpHandler {
    private final Session session = new ConsoleSession();
    private final String successMessage = "Logout done!";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        session.removeCurrentSessionUser();

        OutputStream responseBody = exchange.getResponseBody();
        exchange.sendResponseHeaders(200, successMessage.getBytes().length);
        responseBody.write(successMessage.getBytes());
        responseBody.close();
    }
}
