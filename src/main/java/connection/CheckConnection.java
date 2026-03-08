package connection;

import Exception. *;


public interface CheckConnection {

     boolean isReachable(String url) throws CustomIOException, CustomInterruptedException, CustomRuntimeException;

     void setStatusCode(int status);

     int getStatusCode();
}
