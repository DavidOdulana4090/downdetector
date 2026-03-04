package logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import  java.util.logging.Level;
import java.util.logging.Logger;
import Exception.CustomIOException;


public class Fileobj extends ConfigLoader {

    public Fileobj(String filename) throws CustomIOException {
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

    public Collection<Object> getKeySet() {
        return ConfigProperties.keySet();
    }

    @Override
    public String getProperty(String key) throws CustomIOException {
        if(ConfigProperties.getProperty(key.toLowerCase()) == null) {
            throw new CustomIOException("\nProperty: " + key  + " of file: " + getfilename() + " not found");
        } else {
            return ConfigProperties.getProperty(key);
        }
    }
}
