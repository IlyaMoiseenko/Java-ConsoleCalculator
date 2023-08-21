package application.http.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import interfaces.Session;
import services.OperationService;
import session.ConsoleSession;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public class HistoryHandler implements HttpHandler {
    private final OperationService operationService = new OperationService();
    private final Session session;

    public HistoryHandler(Session session) {
        this.session = session;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        OutputStream responseBody = exchange.getResponseBody();
        StringBuilder historyInBytes = new StringBuilder();
        List<String> historyByUser = operationService.getHistoryByUser(session.getUser());

        for (String history : historyByUser) {
            historyInBytes.append(history).append("\n");
        }

        exchange.sendResponseHeaders(200, historyInBytes.toString().getBytes().length);

        responseBody.write(historyInBytes.toString().getBytes());
        responseBody.close();
    }
}
