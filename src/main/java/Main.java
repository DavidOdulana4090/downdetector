import connection.TestConnection;
import logging.Fileobj;
import Exception.CustomIOException;
import logging.outfile;

import java.io.IOException;
import java.net.HttpURLConnection;

public class Main {
    public static void main(String[] args) throws IOException {

        HttpURLConnection connection1 = null;
        TestConnection connection = new TestConnection();
        Fileobj config = new Fileobj("text.txt");

        outfile.logsuccessfulResult(connection1);

    }
}
