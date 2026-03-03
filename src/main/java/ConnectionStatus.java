import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

interface ConnectionStatus {

    public int TIMEOUT = 3000;

    public boolean isReachable(Property property);

    public boolean isReachable(String url);

    public void saveContent();
}

class testConnection implements ConnectionStatus {

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
            throw new CustomRuntimeExpection("error " + e);
        }
        return response >= 200 && response <= 399;
    }

    @Override
    public void saveContent() {

    }
}