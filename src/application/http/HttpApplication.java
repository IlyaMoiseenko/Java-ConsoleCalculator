package application.http;

import application.http.handler.*;
import com.sun.net.httpserver.HttpServer;
import interfaces.Session;
import session.ConsoleSession;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpApplication {
    private final Session session = new ConsoleSession();

    public void start() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

            httpServer.createContext("/register", new RegisterHandler());
            httpServer.createContext("/login", new LoginHandler(session));
            httpServer.createContext("/calculator", new CalculatorHandler(session));
            httpServer.createContext("/history", new HistoryHandler(session));
            httpServer.createContext("/logout", new LogoutHandler(session));

            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
