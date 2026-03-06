package datafilehandling;

import connection.httpconnection;

import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class createReport extends httpconnection {
    static String directory = "src/main/logs";
    static Path dirPath = Paths.get(directory);
    static String filename = "log.txt";
    static FileWriter txt;

    public enum outputType{
        LOGFILE,
        CONSOLE
    }

    public static void mkfile() {
        try {
            if(!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
        } catch (Exception e) {

        }
    }

    public static void createFile(HttpURLConnection connection) throws IOException {
        try {
            createReport.mkfile();
            txt = new FileWriter(directory + "/" + filename);
            txt.write("");
        } catch (Exception e) {
            System.out.println("error " + e);
        } finally {
            txt.close();
        }
    }
}
