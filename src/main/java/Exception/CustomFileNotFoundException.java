package Exception;

public class CustomFileNotFoundException extends RuntimeException {
    public CustomFileNotFoundException(String message) {
        super(message);
    }
}
