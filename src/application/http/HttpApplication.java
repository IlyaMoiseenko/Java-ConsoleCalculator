package application.http;

import application.http.handler.*;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpApplication {
    public void start() {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

            httpServer.createContext("/register", new RegisterHandler());
            httpServer.createContext("/login", new LoginHandler());
            httpServer.createContext("/calculator", new CalculatorHandler());
            httpServer.createContext("/history", new HistoryHandler());
            httpServer.createContext("/logout", new LogoutHandler());

            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
