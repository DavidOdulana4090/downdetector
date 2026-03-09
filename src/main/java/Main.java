import connection.CheckConnection;
import connection.HttpConnection;
import datafilehandling.*;
import Exception. *;

public class Main {
    public static void main(String[] args) throws CustomIOException {


        CheckConnection connection = new HttpConnection();

        boolean result = connection.isReachable("https://www.google.com");

        System.out.println(result);
    }
}
