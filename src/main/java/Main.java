public class Main {
    public static void main(String[] args) throws CustomIOException {

        ConnectionStatus testConnection = new testConnection();
        Property newfile = new Property("config.properties");
        Property textfile = new Property("text.txt");
        Property htmlfile = new Property("index.html");

        String format = textfile.getkeys();
     //   boolean result = testConnection.isReachable(htmlfile.getProperty("youtube"));

      //  System.out.println(result);
        System.out.println(format);

    }
}
