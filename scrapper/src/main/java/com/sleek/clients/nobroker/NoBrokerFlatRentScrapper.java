package com.sleek.clients.nobroker;

import com.sleek.APIScrapper;
import com.sleek.Scrapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Example for search query params for NoBroker
 * <p>
 * pageNo: 1
 * searchParam: W3sibGF0IjoxMi45MzUxOTI5LCJsb24iOjc3LjYyNDQ4MDY5OTk5OTk5LCJwbGFjZUlkIjoiQ2hJSkxmeVkyRTRVcmpzUlZxNEFqSTd6Z1JZIiwicGxhY2VOYW1lIjoiS29yYW1hbmdhbGEifV0=
 * radius: 2.0
 * sharedAccomodation: 0
 * city: bangalore
 * locality: Koramangala
 * <p>
 *
 * Here, the search param - W3sibGF0IjoxMi45MzUxOTI5LCJsb24iOjc3LjYyNDQ4MDY5OTk5OTk5LCJwbGFjZUlkIjoiQ2hJSkxmeVkyRTRVcmpzUlZxNEFqSTd6Z1JZIiwicGxhY2VOYW1lIjoiS29yYW1hbmdhbGEifV0
 * decodes to the following through Base64 encoding:
 * [{"lat":12.9351929,"lon":77.62448069999999,"placeId":"ChIJLfyY2E4UrjsRVq4AjI7zgRY","placeName":"Koramangala"}]
 */

public class NoBrokerFlatRentScrapper {
    private static final Logger logger = LoggerFactory.getLogger(NoBrokerFlatRentScrapper.class.getName());
    private static final String API_ENDPOINT = "https://www.nobroker.in/api/v3/multi/property/RENT/filter";
    private static final String DATA_DIV_IN_API_RESPONSE = "data";
    private static final String HTML_BODY = "body";
    private final Scrapper scrapper;
    private final QueryParams params;

    public NoBrokerFlatRentScrapper(final String city, final String locality) throws Exception {
        this.params = new QueryParams(city, locality);
        this.scrapper = new APIScrapper(API_ENDPOINT, params.getQueryParamsMap());
    }

    public void getResponse() {
        try {
            final String apiResponseText = scrapper.getResponseForGETRequest();
            final Document responseDocument = Jsoup.parse(apiResponseText);
            final JSONArray jsonDataArray = (JSONArray) new JSONObject(
                    responseDocument.select(HTML_BODY)
                            .get(0)
                            .childNodes()
                            .get(0)
                            .toString())
                    .get(DATA_DIV_IN_API_RESPONSE);
            final NoBrokerRentResponseBuilder response = new NoBrokerRentResponseBuilder(
                    new NoBrokerRentRequest(params.getCity(), params.getLocality()));

            System.out.println("json array " + String.valueOf(jsonDataArray.length()));
            for (int i = 0; i < jsonDataArray.length(); ++i) {
                final int idx = i;
                final StringBuilder attributeValue = new StringBuilder();
                final AtomicInteger dataLen = new AtomicInteger();
                final AtomicInteger errorLen = new AtomicInteger();
                Arrays.stream(PropertyAttribute.values()).forEach(attribute -> {
                    try {
                        attributeValue.append(new JSONObject(jsonDataArray.get(idx).toString()).get(attribute.getString()).toString());
                        dataLen.incrementAndGet();
                    } catch (final JSONException ignored) {
                        errorLen.incrementAndGet();
                    }
                    response.addEntry(attribute, attributeValue.toString());
                });
                System.out.println("dataLen " + dataLen.get());
                System.out.println("errorLen " + errorLen.get());
            }
        } catch (final Exception e) {
            logger.error("Unable to get response for params {}", params, e);
        }
    }
}
