public class Main {
    public static void main(String[] args) throws CustomIOException {

        ConnectionStatus testConnection = new testConnection();
        Property newfile = new Property("config.properties");
        Property textfile = new Property("text.txt");

        testConnection.isReachable(newfile.getProperty("youtube"));

    }
}
