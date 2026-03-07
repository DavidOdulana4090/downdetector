package connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import Exception. *;
import datafilehandling.createReport;

public class httpconnection implements Checkconnections {

    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> response;
    boolean reachable;
    int timeout;
    StringBuilder sb;
    int statusCode;

    @Override
    public boolean isReachable(String url) throws CustomInterruptedException, CustomIOException, CustomRuntimeException {
        try {
             request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            setResponse(response);
            setStatusCode(response.statusCode());

            if (statusCode >= 200 && statusCode < 300) {
                reachable = true;
            }
            return reachable;
        } catch (Exception e){
            System.err.println("not a url " + e.getMessage());
            setResponse(null);
            setStatusCode(0);
            return false ;
        }
    }

    @Override
    public HttpResponse<String> getResponse() {
        return response;
    }

    @Override
    public void setResponse(HttpResponse<String> response) {
        this.response = response;
    }

    @Override
    public void setStatusCode(int status){
        this.statusCode = status;
    }

    @Override
    public int getStatusCode(){
        return statusCode;
    }
}
