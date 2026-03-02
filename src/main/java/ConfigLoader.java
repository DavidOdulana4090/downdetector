import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static Properties ConfigProperties = new Properties();

    ConfigLoader(String filename) {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(filename)){

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
