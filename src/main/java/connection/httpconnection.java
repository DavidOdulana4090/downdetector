package connection;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

import Exception. *;

public class httpconnection implements Checkconnections {

    HttpClient httpClient = HttpClient.newHttpClient();
    HttpRequest request;
    HttpResponse<String> response;
    boolean reachable;
    int timeout;
    StringBuilder sb;

    @Override
    public void checkAllServices(File file) throws CustomIOException, CustomFileNotFoundException {
        try (Scanner scanner = new Scanner(file)){
            while (scanner.hasNextLine()) {
                String txtline = scanner.nextLine();
                System.out.println(txtline + " " + (isReachable(txtline) ? "is reachable" : "is not reachable"));
            }
        } catch (FileNotFoundException e){
            System.err.println("file not found: " + e.getMessage());
        }
    }

    @Override
    public boolean isReachable(String url) throws CustomInterruptedException, CustomIOException, CustomRuntimeException {
        try {
             request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .GET()
                    .build();
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() >= 200 && response.statusCode() < 300) {
                reachable = true;
            }
            return reachable;
        } catch (Exception e){
            System.err.println("not a url " + e.getMessage());
            return false ;
        }
    }
}

