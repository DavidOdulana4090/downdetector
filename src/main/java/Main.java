public class Main {
    public static void main(String[] args) throws CustomIOException {

        Property newfile = new Property("config.properties");
        Property textfile = new Property("text.txt");

        System.out.println(textfile.getProperty("youtube"));

    }
}
