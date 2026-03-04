package Exception;

import java.io.IOException;

public class CustomIOException extends IOException {
    public CustomIOException(String message) {
        super(message);
    }
}
