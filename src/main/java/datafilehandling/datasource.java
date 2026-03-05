package datafilehandling;
import Exception. *;

abstract class datasource {

    public abstract void loadsource(String sourcename) throws CustomIOException, CustomFileNotFoundException;

    public abstract String getfileSource();

}
