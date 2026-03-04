import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

interface ConnectionStatus {

    public int TIMEOUT = 3000;

    public String isReachable(Fileobj file) throws CustomIOException;

    public boolean isReachable(String url);
}

class TestConnection implements ConnectionStatus {

    StringBuilder stringBuilder = new StringBuilder();
    int index;

    @Override
    public String isReachable(Fileobj property) throws CustomIOException {
        for (String key : property.ConfigProperties.stringPropertyNames()) {
            index++;
            stringBuilder.append(index)
                    .append(". ")
                    .append(key)
                    .append(" = ")
                    .append(isReachable(property.getProperty(key)))
                    .append("\n");
        }
        return stringBuilder.toString();
    }
    @Override
    public boolean isReachable(String url) {
        int response;
        try {
           HttpURLConnection connection = (HttpURLConnection) URI.create(url.toLowerCase()).toURL().openConnection();
           connection.setRequestMethod("HEAD");
           connection.setConnectTimeout(TIMEOUT);
           connection.setReadTimeout(TIMEOUT);
           response = connection.getResponseCode();

        } catch (Exception e) {
            response = -1;
        }
        return response >= 200 && response <= 399;
    }
}


//    public static Path logPath = Paths.get("src/main/resources/dir");
//
//    public void GetConnectionLogfile(Fileobj file) throws CustomIOException {
//        try {
//            if (!Files.exists(logPath)) {
//                Files.createDirectories(logPath);
//                System.out.println("Log folder created at: " + logPath.toAbsolutePath());
//            }
//        } catch (Exception e) {
//            throw new CustomIOException("Could not create log directory: " + e.getMessage());
