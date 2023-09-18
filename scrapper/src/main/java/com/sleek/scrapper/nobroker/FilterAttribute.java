package com.sleek.scrapper.nobroker;

public enum FilterAttribute {
    PAGE("pageNo"),
    SEARCH_PARAM("searchParam"),
    RADIUS("radius"),
    CITY("city"),
    LOCALITY("locality"),
    SHARED_ACCOMODATION("sharedAccomodation");

    private final String attribute;
    FilterAttribute(final String attribute) {
        this.attribute = attribute;
    }

    public FilterAttribute getFilter(final String filter) {
        return FilterAttribute.valueOf(filter);
    }

    public String getString() {
        return attribute;
    }
}