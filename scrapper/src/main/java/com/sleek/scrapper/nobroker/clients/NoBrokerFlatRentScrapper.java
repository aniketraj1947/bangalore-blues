package com.sleek.scrapper.nobroker.clients;

import com.sleek.scrapper.nobroker.APIScrapper;
import com.sleek.scrapper.nobroker.PropertyAttribute;
import com.sleek.scrapper.nobroker.QueryParams;
import com.sleek.scrapper.nobroker.Scrapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
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

    public NoBrokerFlatRentScrapper(final String city,
                                    final String locality,
                                    final int page) throws Exception {
        this.params = new QueryParams(city, locality, page);
        this.scrapper = new APIScrapper(API_ENDPOINT, params.getQueryParamsMap());
    }

    public NoBrokerRentResponseBuilder getResponse() {
        try {
            final long ts = System.currentTimeMillis();
            final String apiResponseText = scrapper.getResponseForGETRequest();
            System.out.println("time for api " + (System.currentTimeMillis() - ts));
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
            final long ts2 = System.currentTimeMillis();
            System.out.println("total length " + jsonDataArray.length());
            for (int i = 0; i < jsonDataArray.length(); ++i) {
                final int idx = i;
                final AtomicInteger dataLen = new AtomicInteger();
                final AtomicInteger errorLen = new AtomicInteger();
                final Map<PropertyAttribute, String> propertyDetails = new HashMap<>();
                final JSONObject jsonObject = new JSONObject(jsonDataArray.get(idx).toString());
                Arrays.stream(PropertyAttribute.values()).forEach(attribute -> {
                    try {
                        propertyDetails.put(attribute, jsonObject.get(attribute.getString()).toString());
                        dataLen.incrementAndGet();
                    } catch (final JSONException ignored) {
                        errorLen.incrementAndGet();
                    }

                });
                response.addEntry(propertyDetails);
                System.out.println("error stats " + dataLen.get() + " " + errorLen.get());
            }
            System.out.println("time for loop " + (System.currentTimeMillis() - ts2));

            return response;
        } catch (final Exception e) {
            logger.error("Unable to get response for params {}", params, e);
        }
        return null;
    }
}
