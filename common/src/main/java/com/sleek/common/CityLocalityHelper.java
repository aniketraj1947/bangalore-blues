package com.sleek.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class CityLocalityHelper {

    private static final Logger logger = LoggerFactory.getLogger(CityLocalityHelper.class.getName());
    private static final String LOCALITY_RESOURCE = "/Users/raniket/Documents/github-personal/bangalore-blues/common/src/main/resources/locality/";
    private static final String SEPARATOR = "/";
    private static final String FILE_FORMAT = ".txt";
    private static final String PLACE_NAMES = "place-names";
    private static final String PLACE_IDS = "place-ids";
    private final City city;
    private final Map<String, String> localities;

    public CityLocalityHelper(final String city) {
        if (City.getEnum(city) == null) {
            throw new IllegalArgumentException("Unknown city " + city);
        }
        this.city = City.getEnum(city);
        this.localities = new HashMap<>();
        populateLocalities();
    }

    private void populateLocalities() {
        String localityName;
        String localityId;
        try (final BufferedReader placeNameReader =
                     new BufferedReader(new FileReader(
                             LOCALITY_RESOURCE + city + SEPARATOR + PLACE_NAMES + FILE_FORMAT));
             final BufferedReader placeIdReader =
                     new BufferedReader(new FileReader(
                             LOCALITY_RESOURCE + city + SEPARATOR + PLACE_IDS + FILE_FORMAT))) {
            while ((localityName = placeNameReader.readLine()) != null && (localityId = placeIdReader.readLine()) != null) {
                localities.put(localityName.toLowerCase(), localityId);
            }
        } catch (final IOException e) {
            logger.error("Error while populate locality data for {}", city, e);
        }
    }

    public City getCity() {
        return city;
    }

    public Set<String> getLocalities() {
        return localities.keySet();
    }

    public String getPlaceIdForLocality(final String locality) {
        final String localityFormatted = locality.toLowerCase();
        return (isLocalityPresent(locality) && localities.containsKey(localityFormatted)) ?
                localities.get(localityFormatted) : null;
    }

    public boolean isLocalityPresent(final String locality) {
        return localities.entrySet().stream().anyMatch(l ->
                Pattern.compile(Pattern.quote(locality), Pattern.CASE_INSENSITIVE).matcher(l.getKey()).find());
    }
}
