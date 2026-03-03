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

           System.out.println(connection.getResponseCode());
        } catch (Exception e) {
            throw new CustomRuntimeExpection("error " + e);
        }
        return false;
    }

    @Override
    public void saveContent() {

    }
}