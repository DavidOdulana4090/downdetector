package connection;

import datafilehandling.Readfile;

import Exception. *;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.http.HttpResponse;


public interface Checkconnections {

     boolean isReachable(String url) throws CustomIOException, CustomInterruptedException, CustomRuntimeException;

     void setStatusCode(int status);

     int getStatusCode();
}
