package com.sleek;

import com.google.maps.GeoApiContext;
import com.google.maps.PlacesApi;
import com.google.maps.model.PlaceDetails;
import com.google.maps.model.PlacesSearchResponse;
import com.google.maps.model.PlacesSearchResult;
import com.sleek.common.CityLocalityHelper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class CityLocalityHelperTest {

    @Test
    public void testCityLocalityHelper() throws Exception {
        final CityLocalityHelper helper = new CityLocalityHelper("bangalore");
        Assert.assertEquals(99, helper.getLocalities().size());
        Assert.assertTrue(helper.isLocalityPresent("koramangala"));
        Assert.assertEquals("ChIJLfyY2E4UrjsRVq4AjI7zgRY", helper.getPlaceIdForLocality("koramangala"));
        Assert.assertTrue(helper.isLocalityPresent("Devanahalli"));
        Assert.assertEquals("ChIJG5NCqX0drjsRwMNCtQZ6U7k", helper.getPlaceIdForLocality("Devanahalli"));
        Assert.assertFalse(helper.isLocalityPresent("Balewadi"));
        Assert.assertNull(helper.getPlaceIdForLocality("Balewadi"));
    }

    @Test
    @Ignore
    public void getPlaceIds() {
        // Replace YOUR_API_KEY with your actual API key
        String apiKey = "AIzaSyC8O4aVyhMVt8gJUo9PfpyAYRTm7ykT9nM";

        // Replace these with the names of your 20 cities
        List<String> cities = Arrays.asList(
                "Koramangala", "Indiranagar", "Jayanagar", "Malleshwaram", "Basavanagudi",
                "Rajajinagar", "Whitefield", "Electronic City", "HSR Layout", "MG Road",
                "BTM Layout", "Marathahalli", "Banashankari", "JP Nagar", "Hebbal", "Yelahanka",
                "Shivajinagar", "Cunningham Road", "Frazer Town", "Bannerghatta Road"
        );

        GeoApiContext context = new GeoApiContext.Builder().apiKey(apiKey).build();

        for (String city : cities) {
            try {
                PlacesSearchResponse placesSearchResponse = PlacesApi.textSearchQuery(context, city).await();

                if (placesSearchResponse.results != null && placesSearchResponse.results.length > 0) {
                    PlacesSearchResult place = placesSearchResponse.results[0];
                    PlaceDetails placeDetails = PlacesApi.placeDetails(context, place.placeId).await();

                    System.out.println("City: " + city);
                    System.out.println("Place ID: " + placeDetails.placeId);
                    System.out.println("-------------");
                } else {
                    System.out.println("No results found for " + city);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}