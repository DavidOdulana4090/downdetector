package datafilehandling;
import Exception. *;

abstract class AbstractSource {

    public abstract void loadSource(String sourcename) throws CustomIOException, CustomFileNotFoundException;

    public abstract void processResult(String line) throws CustomIOException;

}
