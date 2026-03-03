import java.net.HttpURLConnection;
import java.net.URL;

interface ConnectionStatus {

    public int TIMEOUT = 36000;

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
        try {
           HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
           connection.setRequestMethod("HEAD");
           connection.setConnectTimeout(TIMEOUT);
           connection.setReadTimeout(TIMEOUT);

           int response = connection.getResponseCode();
           return response == HttpURLConnection.HTTP_OK;

        } catch (Exception e) {
            throw new CustomRuntimeExpection("error " + e);
        }
    }

    @Override
    public void saveContent() {

    }
}