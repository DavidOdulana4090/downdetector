import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

abstract class ConfigLoader {

    ConfigLoader(String filename) {
        this.filename = filename;
    }

    public Properties ConfigProperties = new Properties();

    public String filename;

    public abstract void loadfile() throws CustomIOException;

    public abstract String getfilename();

    public abstract Properties getProperties();

    public abstract String getProperty(String key);
}

class Property extends ConfigLoader {

    Property(String filename) throws CustomIOException {
        super(filename);
        loadfile();
    }

    @Override
    public void loadfile() throws CustomIOException {
        try (InputStream inputStream = ConfigLoader.class.getClassLoader().getResourceAsStream(filename)) {
            if (inputStream == null) {
                throw new CustomIOException("File not Found: " + filename);
            } else {
                ConfigProperties.load(inputStream);
                System.out.println(filename + " loaded");
            }
        } catch (IOException error) {
            throw new CustomIOException("Something went wrong " + error);
        }
    }

    @Override
    public String getfilename() {
        return super.filename;
    }

    @Override
    public Properties getProperties() {
        return ConfigProperties;
    }

    @Override
    public String getProperty(String key) {
        return ConfigProperties.getProperty(key);
    }
}
