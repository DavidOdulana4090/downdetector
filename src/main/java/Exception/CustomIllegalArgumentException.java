package Exception;

public class CustomIllegalArgumentException extends IllegalArgumentException {
    public CustomIllegalArgumentException(String message) {
        super(message);
    }
}
