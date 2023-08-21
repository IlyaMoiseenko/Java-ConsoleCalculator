package application.http.util;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HttpResponseBodyUtil {
    public static void setSuccessMessage(HttpExchange httpExchange, String successMessage) throws IOException {
        try {
            OutputStream responseBody = httpExchange.getResponseBody();
            httpExchange.sendResponseHeaders(200, successMessage.getBytes().length);
            responseBody.write(successMessage.getBytes());
            responseBody.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
