package datafilehandling;

import Exception.CustomIOException;

import java.io.File;
import java.util.Scanner;
import connection. *;
import Exception. *;

public class ReadTxtFile extends AbstractSource {

    private File file;
    private String filepath;
    private Scanner scanner;
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
    public void loadSource(String filename) {
        String txtline = null;
        try {
            var resource = getClass().getClassLoader().getResource(filename);
            assert resource != null;

            file = new File(resource.toURI());
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        processResult(scanner.nextLine());
                    }
                    System.out.println("Finished ");
                }
                catch (Exception e) {
                    System.err.println("cant read file" + e);
                }
        } catch (Exception e) {
            System.err.println("file not found: " + filename +  " " + e.getMessage());
            System.exit(0);
        }
    }

    @Override
    public void processResult(String line) throws CustomIOException {
        if (!input.equalsIgnoreCase("y")){
            System.out.println(line + " " + (httpconnection.isReachable(line) ? "is reachable" : "is not reachable"));
        } else {
            CreateReport.log(line, (httpconnection.isReachable(line)), httpconnection.getStatusCode());
        }
    }


}
