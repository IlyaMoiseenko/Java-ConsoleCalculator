import application.console.ConsoleApplication;
import application.http.HttpApplication;

public class Main {
    public static void main(String[] args) {
//        ConsoleApplication application = new ConsoleApplication();
//        application.start();

        HttpApplication httpApplication = new HttpApplication();
        httpApplication.start();
    }
}
