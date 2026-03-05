import datafilehandling.Readfile;
import connection. *;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Readfile readfile = new Readfile("text.txt");
        Checkconnections httpconnection = new httpconnection();

        boolean result = httpconnection.isReachable("https://www.google.com");

        System.out.println(result);

    }
}
