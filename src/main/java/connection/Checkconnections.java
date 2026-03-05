package connection;

import datafilehandling.Readfile;

import Exception. *;


public interface Checkconnections {

     String checkAllServices(Readfile file);

     boolean isReachable(String url) throws CustomIOException, CustomInterruptedException, CustomRuntimeException;
}
