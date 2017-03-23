package potlemon.server.app.exceptions;

/**
 * Basic exception for the server.
 */
public class ServerException extends Exception {
    public ServerException(String message) {
        this(message,null);
    }

    public ServerException(String message, Throwable cause) {
        super("[POTLEMON SERVER] " + message, cause);
    }
}
