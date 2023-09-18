package com.sleek.common;

import java.util.Arrays;
import java.util.Optional;

public enum City {
    BANGALORE("bangalore");

    private final String city;

    City(final String city) {
        this.city = city;
    }

    public static City getEnum(final String city) {
        final Optional<City> opt = Arrays.stream(City.values()).filter(e ->
                e.getString().matches(city.toLowerCase())).findFirst();
        return opt.orElse(null);
    }

    public String getString() {
        return city;
    }
}