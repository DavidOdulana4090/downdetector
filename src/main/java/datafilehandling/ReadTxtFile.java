package datafilehandling;

import Exception.CustomFileNotFoundException;
import Exception.CustomIOException;
import connection.HttpConnection;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;
import connection. *;
import Exception. *;

public class ReadTxtFile extends AbstractSource {

    private File file;
    protected String filename;
    protected HttpConnection httpconnection = new HttpConnection();
    String input;


    public ReadTxtFile() throws CustomIOException, CustomFileNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter filename : ");
            filename = scanner.nextLine();

            System.out.println("Do you wish to make a report of this file (Y/N) : ");
            input = scanner.nextLine();

            System.out.println();
            loadSource(filename);
        }
    }

    @Override
    public void loadSource(String filename) throws CustomIOException, CustomFileNotFoundException {
        try {
            var resource = getClass().getClassLoader().getResource(filename);
            if (resource == null) {
                throw new CustomFileNotFoundException("File not found in resources: " + filename);
            }

            file = new File(resource.toURI());
            try (FileInputStream fileInputStream = new FileInputStream(file);
                 BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                 DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
                 BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(dataInputStream))) {

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    processResult(line);
                }
                System.out.println("Finished.");

            } catch (IOException e) {
                throw new CustomIOException("read file error " + e.getMessage());
            }
        } catch (URISyntaxException e) {
            throw new CustomIOException("loading file error " + e.getMessage());
        }
    }

    @Override
    public void processResult(String line) throws CustomIOException {
        if (!input.equalsIgnoreCase("y")){
            String formatted = String.format("url: %-35s status: %-20s", line, (httpconnection.isReachable(line) ? "is reachable" : "is not reachable"));
            System.out.println(formatted);
        } else {
            CreateReport.log(line, (httpconnection.isReachable(line)), httpconnection.getStatusCode());
        }
    }
}
