package com.sleek.scrapper.nobroker.clients;

public class NoBrokerRentRequest {
    private final String city;
    private final String locality;

    public NoBrokerRentRequest(final String city, final String locality) {
        this.city = city;
        this.locality = locality;
    }
}