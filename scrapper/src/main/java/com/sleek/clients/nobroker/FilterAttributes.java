package com.sleek.clients.nobroker;

public enum FilterAttributes {
    PAGE("pageNo"),
    SEARCH_PARAM("SEARCH_PARAM"),
    RADIUS("radius"),
    CITY("city"),
    LOCALITY("locality"),
    SHARED_ACCOMODATION("sharedAccomodation");

    private final String attribute;
    FilterAttributes(String attribute) {
        this.attribute = attribute;
    }

    public FilterAttributes getFilter(final String filter) {
        return FilterAttributes.valueOf(filter);
    }
}