package logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import Exception.CustomIOException;

public class outfile {
    static String directory = "src/main/logs";
    static Path dirPath = Paths.get(directory);
    static String filename = "log.txt";
    static FileWriter txt;

    public static void mkfile() {
        try {
            if(!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
        } catch (Exception e) {

        }
    }

    public static void logsuccessfulResult(HttpURLConnection connection) throws IOException {
        try {
            outfile.mkfile();
            txt = new FileWriter(directory + "/" + filename);
            txt.write("test");
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            txt.close();
        }
    }

    public static void logfailedResult(HttpURLConnection connection) throws CustomIOException {

    }
}
