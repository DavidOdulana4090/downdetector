import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

interface ConnectionStatus {

    public int TIMEOUT = 3000;

    public boolean isReachable(Property property);

    public boolean isReachable(String url);

}

class TestConnection implements ConnectionStatus {

    @Override
    public boolean isReachable(Property property) {
        return false;
    }

    @Override
    public boolean isReachable(String url) {
        int response;
        try {
           HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
           connection.setRequestMethod("HEAD");
           connection.setConnectTimeout(TIMEOUT);
           connection.setReadTimeout(TIMEOUT);
           response = connection.getResponseCode();

        } catch (Exception e) {
            throw new CustomRuntimeExpection("error " + e.getMessage());
        }
        return response >= 200 && response <= 399;
    }
}

class ConnectionInfo extends TestConnection {
    Path logPath = Paths.get("src/main/resources/log");
    public void getconnectionlogfile(Property property) throws IOException {
        try {
            if (!Files.exists(logPath)) {
                Files.createDirectories(logPath);
                System.out.println("Log folder created at: " + logPath.toAbsolutePath());
            }
        } catch (Exception e) {
            throw new CustomIOException("Could not create log directory: " + e.getMessage());
        }
    }
}