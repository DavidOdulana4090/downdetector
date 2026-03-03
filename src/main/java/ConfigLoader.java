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

    public abstract String getkeys();

    public abstract String getvalues();

    public abstract String getProperty(String key) throws CustomIOException;
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
    public String getkeys() {
        StringBuilder keys = new StringBuilder();
        for (String key : ConfigProperties.stringPropertyNames()){
            keys.append("Key = ")
                    .append(key)
                    .append("\n");
        }
        return keys.toString();
    }

    @Override
    public String getvalues() {
        StringBuilder values = new StringBuilder();
        for (String key : ConfigProperties.stringPropertyNames()){
            values.append("Value = ")
                    .append(ConfigProperties.getProperty(key)).
                    append("\n");
        }
        return values.toString();
    }

    @Override
    public String getProperty(String key) throws CustomIOException {
        if(ConfigProperties.getProperty(key) == null) {
            throw new CustomIOException("\nProperty: " + key  + "\n of file: " + getfilename() + " not found");
        } else {
            return ConfigProperties.getProperty(key);
        }
    }
}
