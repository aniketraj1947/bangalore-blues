package com.sleek.clients.nobroker;

import com.sleek.common.CityLocalityHelper;
import com.sleek.scrapper.nobroker.clients.NoBrokerFlatRentScrapper;
import com.sleek.scrapper.nobroker.QueryParams;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Set;

public class NoBrokerFlatRentScrapperTest {

    @Test
    public void testSearchParam() throws Exception {
        final String city = "bangalore";
        final String locality = "JP Nagar";
        Assert.assertEquals("W3sibGF0IjoiMTIuOTM1MTkyOSIsImxvbiI6Ijc3LjYyNDQ4MDY5OTk5OTk5IiwicGxhY2VJZCI6IkNoSUpLNmRKY3cwVnJqc1JSajNkNGFFLTBQTSIsInBsYWNlTmFtZSI6IkpQIE5hZ2FyIn1d",
                QueryParams.getSearchParam(city, locality));
    }

    @Test
    public void testNoBrokerScrapper() throws Exception {
        final NoBrokerFlatRentScrapper scrapper = new NoBrokerFlatRentScrapper("bangalore", "Whitefield");
        scrapper.getResponse();
    }

    @Test
    @Ignore
    public void getPlaceIdScrapper() throws Exception {
//        final NoBrokerFlatRentScrapper scrapper = new NoBrokerFlatRentScrapper("pune", "balewadi");
        // System.out.println(scrapper.getResponse());
        final String url = "https://www.google.com/maps/place/";
        final String suffix = "+Bangalore";
        final CityLocalityHelper helper = new CityLocalityHelper("bangalore");
        try {

            final Set<String> localities = helper.getLocalities();
            int count = 0;
            for (final String p : localities) {
                final String fUrl = url + p.replace(" ","+") + suffix;
                System.out.println("Count " +  count + "...trying " + fUrl);
                String htmlContent = fetchHtml(fUrl);
                int idxStart = htmlContent.indexOf("ChIJ");
                int st=idxStart;
                if (idxStart == -1) {
                    System.out.println("Error for " + fUrl + " start " + idxStart + " end " + st);
                    continue;
                }
                while(htmlContent.charAt(idxStart) != '\\') {
                    idxStart--;
                }
                while(htmlContent.charAt(st) != '\\') {
                    st++;
                }
                try {
                    System.out.println(htmlContent.substring(idxStart+2, st));
                } catch (Exception e) {
                    System.out.println("Error for " + fUrl + " start " + idxStart + " end " + st);
                }

                Thread.sleep(2000);
                ++count;
            }

        } catch (IOException e) {
            System.err.println("Error fetching HTML content: " + e.getMessage());
        }
    }

    public static String fetchHtml(String urlString) throws IOException {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = in.readLine()) != null) {
                    content.append(line);
                }
            }
            return content.toString();
        } else {
            throw new IOException("HTTP request failed with status code " + responseCode);
        }
    }

}