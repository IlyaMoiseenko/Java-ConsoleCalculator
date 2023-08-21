package application.http.handler;

import application.http.util.HttpResponseBodyUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import interfaces.Session;
import session.ConsoleSession;

import java.io.IOException;

public class LogoutHandler implements HttpHandler {
    private final Session session;

    public LogoutHandler(Session session) {
        this.session = session;
    }

    private final String successMessage = "Logout done!";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        session.removeCurrentSessionUser();

        HttpResponseBodyUtil.setSuccessMessage(exchange, successMessage);
    }
}
