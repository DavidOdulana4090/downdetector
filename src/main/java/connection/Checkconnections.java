package connection;

import datafilehandling.Readfile;

import Exception. *;

import java.io.File;
import java.io.FileNotFoundException;


public interface Checkconnections {

     void checkAllServices(File file) throws CustomIOException, FileNotFoundException;

     boolean isReachable(String url) throws CustomIOException, CustomInterruptedException, CustomRuntimeException;
}
