package connection;

import datafilehandling.Readfile;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import Exception. *;

public class httpconnection implements Checkconnections {

    HttpClient client = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> response;
    boolean reachable;
    int timeout;

    @Override
    public String checkAllServices(Readfile file) {
        return "";
    }

    @Override
    public boolean isReachable(String url) throws CustomInterruptedException, CustomIOException, CustomRuntimeException {
        try {
             request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200) {
                return reachable = true;
            }
        } catch (Exception e){
            throw new CustomIOException(e.getMessage());
        }
        return false;
    }
}