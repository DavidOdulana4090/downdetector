package datafilehandling;

import Exception.CustomIOException;

import java.io.File;
import java.util.Scanner;


public class Readfile extends datasource {

    private final String filename;
    private File file;
    private String filepath;

    public Readfile(String filename) {
        this.filename = filename;
    }

    @Override
    public void loadFile() throws CustomIOException {
        try {
            var resource = getClass().getClassLoader().getResource(filename);
            if (resource == null) {
                throw new CustomIOException("file not in resource folder " + filename);
            }

            File file = new File(resource.toURI());

            try (Scanner scanner = new Scanner(file)) {
                while (scanner.hasNextLine()) {
                    String txtline = scanner.nextLine();
                    System.out.println(txtline);
                }
            }
            catch (Exception e) {
                throw new CustomIOException("error reading " + filename + e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String getfileSource() {
        return filepath;
    }
}
