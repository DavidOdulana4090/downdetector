package connection;

import Exception.CustomIOException;
import logging.Fileobj;
import logging.outfile;
import Exception.CustomIllegalArgumentException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;

public class TestConnection implements ConnectionStatus {

    @Override
    public String checkAllServices(Fileobj property) throws CustomIOException {
        StringBuilder stringBuilder = new StringBuilder();
        int index = 0;

        for (String key : property.ConfigProperties.stringPropertyNames()) {
            index++;
            boolean isUp = isReachable(property.getProperty(key));
            stringBuilder.append(index)
                    .append(". ")
                    .append(key)
                    .append(" = ")
                    .append(isUp ? "Reachable" : "Unreachable")
                    .append("\n");
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean isReachable(String url) throws CustomIllegalArgumentException {
        HttpURLConnection connection = null;
        boolean isSuccess;
        try {
            connection = (HttpURLConnection) URI.create(url.trim()).toURL().openConnection();
            connection.setRequestMethod("HEAD");
            connection.setConnectTimeout(TIMEOUT);
            connection.setReadTimeout(TIMEOUT);

            int responseCode = connection.getResponseCode();
            isSuccess = (responseCode >= 200 && responseCode <= 399);

        } catch (IOException | IllegalArgumentException e) {
            System.err.println( url + ": illegal character in url " + e);
            return false;
        }
        finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
        return isSuccess;
    }


}