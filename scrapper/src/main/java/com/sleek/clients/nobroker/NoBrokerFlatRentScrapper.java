package com.sleek.clients.nobroker;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sleek.APIScrapper;
import com.sleek.CityLocalityHelper;
import com.sleek.Scrapper;

import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.sleek.clients.nobroker.FilterAttribute.CITY;
import static com.sleek.clients.nobroker.FilterAttribute.LOCALITY;
import static com.sleek.clients.nobroker.FilterAttribute.PAGE;
import static com.sleek.clients.nobroker.FilterAttribute.RADIUS;
import static com.sleek.clients.nobroker.FilterAttribute.SEARCH_PARAM;
import static com.sleek.clients.nobroker.FilterAttribute.SHARED_ACCOMODATION;

/**
 * Example for search query params
 * <p>
 * pageNo: 1
 * searchParam: W3sibGF0IjoxMi45MzUxOTI5LCJsb24iOjc3LjYyNDQ4MDY5OTk5OTk5LCJwbGFjZUlkIjoiQ2hJSkxmeVkyRTRVcmpzUlZxNEFqSTd6Z1JZIiwicGxhY2VOYW1lIjoiS29yYW1hbmdhbGEifV0=
 * radius: 2.0
 * sharedAccomodation: 0
 * city: bangalore
 * locality: Koramangala
 * <p>
 * W3sibGF0IjoxMi45MzUxOTI5LCJsb24iOjc3LjYyNDQ4MDY5OTk5OTk5LCJwbGFjZUlkIjoiQ2hJSkxmeVkyRTRVcmpzUlZxNEFqSTd6Z1JZIiwicGxhY2VOYW1lIjoiS29yYW1hbmdhbGEifV0
 * [{"lat":12.9351929,"lon":77.62448069999999,"placeId":"ChIJLfyY2E4UrjsRVq4AjI7zgRY","placeName":"Koramangala"}]
 * ChIJLfyY2E4UrjsRVq4AjI7zgRY
 */

public class NoBrokerFlatRentScrapper {
    private static final String API_ENDPOINT = "https://www.nobroker.in/api/v3/multi/property/RENT/filter";
    private static final int DEFAULT_PAGE = 1;
    private static final int DEFAULT_RADIUS = 2;
    private static final int DEFAULT_SHARED_ACCOMMODATION = 0;
    private final Scrapper scrapper;
    private final QueryParams params;

    public NoBrokerFlatRentScrapper(final String city, final String locality) throws Exception {
        this.params = new QueryParams(city, locality);
        this.scrapper = new APIScrapper(API_ENDPOINT, params.getQueryParamsMap());
    }

    public String getResponse() throws IOException {
        return scrapper.getResponseForGETRequest();
    }

    public static final class QueryParams {
        private static final double LAT_DEFAULT = 12.9351929;
        private static final double LON_DEFAULT = 77.62448069999999;
        private static final String LAT = "lat";
        private static final String LON = "lon";
        private static final String PLACE_ID = "placeId";
        private static final String PLACE_NAME = "placeName";
        private static final String JSON_WRAPPER_FORMAT = "[%s]";
        private final int page;
        private final String searchParam;
        private final int radius;
        private final String city;
        private final String locality;
        private final int sharedAccommodation;


        public QueryParams(final int page,
                           final String searchParamHash,
                           final int radius,
                           final String city,
                           final String locality,
                           final int sharedAccommodation) {
            this.page = page;
            this.searchParam = searchParamHash;
            this.radius = radius;
            this.city = city;
            this.locality = locality;
            this.sharedAccommodation = sharedAccommodation;
        }

        public QueryParams(final String city,
                           final String locality) throws Exception {
            this(DEFAULT_PAGE,
                    getSearchParam(city, locality),
                    DEFAULT_RADIUS,
                    city,
                    locality,
                    DEFAULT_SHARED_ACCOMMODATION);
        }

        public Map<String, String> getQueryParamsMap() {
            final Map<String, String> ret = new HashMap<>();
            ret.put(PAGE.getString(), String.valueOf(page));
            ret.put(SEARCH_PARAM.getString(), String.valueOf(searchParam));
            ret.put(RADIUS.getString(), String.valueOf(radius));
            ret.put(CITY.getString(), String.valueOf(city));
            ret.put(LOCALITY.getString(), String.valueOf(locality));
            ret.put(SHARED_ACCOMODATION.getString(), String.valueOf(sharedAccommodation));
            return ret;
        }

        public static String getSearchParam(final String city, final String locality) throws JsonProcessingException {
            final CityLocalityHelper localityHelper = new CityLocalityHelper(city);
            final Map<String, String> searchParam = new LinkedHashMap<>();
            searchParam.put(LAT, String.valueOf(LAT_DEFAULT));
            searchParam.put(LON, String.valueOf(LON_DEFAULT));
            searchParam.put(PLACE_ID, localityHelper.getPlaceIdForLocality(locality));
            searchParam.put(PLACE_NAME, locality);
            final String json = new ObjectMapper().writeValueAsString(searchParam);
            return Base64.getEncoder().encodeToString(String.format(JSON_WRAPPER_FORMAT, json).getBytes());
        }
    }
}
