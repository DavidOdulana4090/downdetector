package logging;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Properties;
import Exception.CustomIOException;


public abstract class ConfigLoader {

    public ConfigLoader(String filename) {
        this.filename = filename;
    }

    public Properties ConfigProperties = new Properties();

    public String filename;

    public abstract void loadfile() throws CustomIOException;

    public abstract String getfilename();

    public abstract Collection<Object> getKeySet();

    public abstract String getProperty(String key) throws CustomIOException;
}

