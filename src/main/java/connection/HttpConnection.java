package connection;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import Exception. *;

public class HttpConnection implements CheckConnection {

    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> response;
    boolean reachable;
    int timeout;
    StringBuilder sb;
    int statusCode;

    @Override
    public boolean isReachable(String url) throws CustomInterruptedException, CustomIOException, CustomRuntimeException {
        this.statusCode = 0;
        try {
             request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            setStatusCode(response.statusCode());

            if (statusCode >= 200 && statusCode < 400) {
                reachable = true;
            }
            return reachable;
        } catch (Exception e){
            System.err.println("failed request: " + url + " " + e.getMessage());
            setStatusCode(0);
            return false ;
        }
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
