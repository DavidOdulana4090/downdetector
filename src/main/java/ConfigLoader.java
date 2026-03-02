import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static Properties ConfigProperties = new Properties();
    public static String filename;

    private ConfigLoader() {

    }

    public static void LoadFile(String filename) throws CustomIOException {
        ConfigLoader.filename = filename;
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new CustomIOException("File not Found: " + filename);
            } else {
                ConfigProperties.load(inputStream);
            }
        } catch (IOException error) {
            throw new CustomIOException("Something went wrong " + error);
        }
    }

    public static String GetFilename(){
        return filename;
    }

    public static Properties GetProperties(){
        return ConfigProperties;
    }
}
