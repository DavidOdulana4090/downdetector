import java.io.IOException;

public class CustomIOException extends IOException {
    public CustomIOException(String message) {
        super(message);
    }

    public CustomIOException(){

    }
}

class CustomRuntimeExpection extends RuntimeException {
    public CustomRuntimeExpection(String message) {
        super(message);
    }

    public CustomRuntimeExpection() {
    }

}
