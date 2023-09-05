package com.sleek;

import org.junit.Assert;
import org.junit.Test;

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
}