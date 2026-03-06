package datafilehandling;

import Exception.CustomIOException;

import java.io.File;
import java.util.Scanner;
import connection. *;
import Exception. *;

public class Readfile extends datasource {

    private File file;
    private String filepath;
    private Scanner scanner;
    protected String filename;
    protected httpconnection httpconnection = new httpconnection();
    String input;


    public Readfile() throws CustomIOException, CustomFileNotFoundException {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter filename : ");
            filename = scanner.nextLine();

            System.out.println("Do you wish to make a report of this file (Y/N) : ");
            String input = scanner.nextLine();

            System.out.println();
            loadsource(filename);
            httpconnection.checkAllServices(file);
        }
    }

    @Override
    public void loadsource(String filename) {
        try {
            var resource = getClass().getClassLoader().getResource(filename);
            assert resource != null;

            file = new File(resource.toURI());

            if (!input.equalsIgnoreCase("y")){
                try (Scanner scanner = new Scanner(file)) {
                    while (scanner.hasNextLine()) {
                        String txtline = scanner.nextLine();
                    }
                }
                catch (Exception e) {
                    System.err.println("cant read file" + e);
                }
            } else {

                // Makereport
            }
        } catch (Exception e) {
            System.err.println("file not found: " + filename +  " " + e.getMessage());
            System.exit(0);
        }
    }


    @Override
    public String getfileSource() {
        return filepath;
    }
}
