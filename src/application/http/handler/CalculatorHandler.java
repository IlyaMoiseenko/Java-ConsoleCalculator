package application.http.handler;

import application.http.util.HttpQueryUtil;
import application.http.util.HttpResponseBodyUtil;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import interfaces.Session;
import models.Operation;
import services.OperationService;
import session.ConsoleSession;

import java.io.IOException;
import java.net.URI;

public class CalculatorHandler implements HttpHandler {
    private final OperationService operationService = new OperationService();
    private final Session session;

    public CalculatorHandler(Session session) {
        this.session = session;
    }

    private final String successMessage = "Operation calculate done!";

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        URI requestURI = exchange.getRequestURI();
        String query = requestURI.getQuery();

        double num1 = Double.parseDouble(HttpQueryUtil.getParameterValue("num1", query));
        double num2 = Double.parseDouble(HttpQueryUtil.getParameterValue("num2", query));
        String type = HttpQueryUtil.getParameterValue("type", query);

        Operation operation = new Operation(num1, num2, type, session.getUser().getId());

        operationService.calculate(operation);

        HttpResponseBodyUtil.setSuccessMessage(exchange, successMessage);
    }
}
