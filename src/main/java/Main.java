public class Main {
    public static void main(String[] args) throws CustomIOException {

        ConnectionStatus testConnection = new testConnection();
        Property newfile = new Property("config.properties");
        Property textfile = new Property("text.txt");

        boolean result = testConnection.isReachable(textfile.getProperty("pinterest"));

        System.out.println(result);

    }
}
