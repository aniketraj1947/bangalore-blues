package com.sleek;

import org.jsoup.HttpStatusException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class APIScrapper extends Scrapper {
    private static final String ADD_QUERY = "?";
    private static final String PARAMS_SEPARATOR = "&";
    private static final String EQUALS = "=";
    private static final String GET = "GET";
    private final Map<String, String> params;

    public APIScrapper(final String url, final Map<String, String> params) {
        this.url = url;
        this.params = params;
    }

    /**
     * Performs a GET request to the specified API endpoint with parameters.
     *
     * @return The API response as a string.
     * @throws IOException if an IO error occurs during the request.
     */
    @Override
    public String getResponseForGETRequest() throws IOException {
        final StringBuilder urlBuilder = new StringBuilder(url);
        urlBuilder.append(ADD_QUERY);
        params.entrySet().stream().forEach(param ->
                urlBuilder.append(param.getKey()).append(EQUALS).append(param.getValue()).append(PARAMS_SEPARATOR));
        final HttpURLConnection connection = (HttpURLConnection) (new URL(urlBuilder.toString()).openConnection());
        connection.setRequestMethod(GET);
        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            final BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            final StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();
            connection.disconnect();
            return responseBuilder.toString();
        } else {
            connection.disconnect();
            throw new HttpStatusException("Invalid response received",
                    connection.getResponseCode(), urlBuilder.toString());
        }
    }

    @Override
    public String getResponseForPOSTRequest() throws IOException {
        throw new UnsupportedOperationException("POST API not implemented");
    }

    @Override
    public String getResponseForPUTRequest() throws IOException {
        throw new UnsupportedOperationException("PUT API implemented");
    }
}