package datafilehandling;
import Exception. *;

abstract class datasource {

    public abstract void loadFile() throws CustomIOException;

    public abstract String getfileSource();

}
