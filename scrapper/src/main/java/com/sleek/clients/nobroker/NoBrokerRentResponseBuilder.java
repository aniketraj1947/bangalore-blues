package com.sleek.clients.nobroker;

import java.util.LinkedHashMap;
import java.util.Map;

public class NoBrokerRentResponseBuilder {
    private final Map<PropertyAttribute, String> responseMap;

    private final NoBrokerRentRequest request;

    public NoBrokerRentResponseBuilder(final NoBrokerRentRequest request) {
        this.request = request;
        this.responseMap = new LinkedHashMap<>();
    }

    public void addEntry(final PropertyAttribute attribute, final String value) {
        responseMap.put(attribute, value);
    }

    public Map<PropertyAttribute, String> getResponseMap() {
        return responseMap;
    }

    public NoBrokerRentRequest getRequest() {
        return request;
    }
}
