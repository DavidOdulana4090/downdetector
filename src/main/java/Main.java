public class Main {
    public static void main() throws CustomIOException {

        ConfigLoader.LoadFile("config.properties");

        System.out.println(ConfigLoader.GetProperties());

    }
}
