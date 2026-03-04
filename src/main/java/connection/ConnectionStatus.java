package connection;

import logging.Fileobj;
import logging.outfile;
import  Exception.CustomIOException;
import java.net.HttpURLConnection;
import java.net.URI;
import Exception.CustomIllegalArgumentException;

interface ConnectionStatus {

    public int TIMEOUT = 3000;

    public String checkAllServices(Fileobj file) throws CustomIOException;

    public boolean isReachable(String url) throws  CustomIOException, CustomIllegalArgumentException;
}
