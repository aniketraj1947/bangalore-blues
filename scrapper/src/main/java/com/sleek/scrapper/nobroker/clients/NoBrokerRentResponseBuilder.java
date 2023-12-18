package com.sleek.scrapper.nobroker.clients;

import com.sleek.scrapper.nobroker.PropertyAttribute;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class NoBrokerRentResponseBuilder {
    private final List<Map<PropertyAttribute, String>> responseMap;

    private final NoBrokerRentRequest request;

    public NoBrokerRentResponseBuilder(final NoBrokerRentRequest request) {
        this.request = request;
        this.responseMap = new ArrayList<>();
    }

    public void addEntry(final Map<PropertyAttribute, String> propertyDetails) {
        responseMap.add(propertyDetails);
    }

    public List<Map<PropertyAttribute, String>> getResponseMap() {
        return responseMap;
    }

    public NoBrokerRentRequest getRequest() {
        return request;
    }
}
