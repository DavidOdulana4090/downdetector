import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ConnectionInfo connectionInfo = new ConnectionInfo();

        Property newfile = new Property("config.properties");
        Property textfile = new Property("text.txt");

        connectionInfo.getconnectionlogfile(textfile);

    }
}
