package christmas.common.logger;

public class Logger {

    private static final String ERROR_MESSAGE_FORMAT = "\n[ERROR] %s\n\n";

    public static void error(String message) {
        System.out.printf(ERROR_MESSAGE_FORMAT, message);
    }
}
