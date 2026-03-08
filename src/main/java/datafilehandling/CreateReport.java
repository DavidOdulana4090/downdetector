package datafilehandling;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CreateReport {
    static String directory = "src/main/logs";
    static Path dirPath = Paths.get(directory);
    static String filename = "log.txt";
    static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    private static void mkfile() {
        try {
            if(!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
        } catch (Exception e) {
            System.err.println("Error creating directory: " + e.getMessage());
        }
    }

    public static void log(String url, boolean isReachable, int statusCode) {
        mkfile();
        String timestamp = LocalDateTime.now().format(dtf);
        String statusStr = isReachable ? "UP" : "DOWN";
        String logLine = String.format("|Time : %-20s URL: %-30s | Status: %-5s | Code: %-3d", timestamp, url, statusStr, statusCode);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(directory + File.separator + filename, true))) {
            writer.write(logLine + "\n");
        } catch (IOException e) {
            System.err.println("Error writing to log file: " + e.getMessage());
        }
    }
}
