import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;

abstract class ConfigLoader {

    ConfigLoader(String filename) {
        this.filename = filename;
    }

    public Properties ConfigProperties = new Properties();

    public String filename;

    public abstract void loadfile() throws CustomIOException;

    public abstract String getfilename();

    public abstract Collection<Object> GetKeySet();

    public abstract String getProperty(String key) throws CustomIOException;
}

class Fileobj extends ConfigLoader {

    Fileobj(String filename) throws CustomIOException {
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

    public Collection<Object> GetKeySet() {
        return ConfigProperties.keySet();
    }

    @Override
    public String getProperty(String key) throws CustomIOException {
        if(ConfigProperties.getProperty(key.toLowerCase()) == null) {
            throw new CustomIOException("\nProperty: " + key  + "\n of file: " + getfilename() + " not found");
        } else {
            return ConfigProperties.getProperty(key);
        }
    }
}
