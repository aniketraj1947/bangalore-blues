package com.sleek.clients.nobroker;

public class NoBrokerFlatRentScrapper {
    private static final String API_ENDPOINT = "https://www.nobroker.in/api/v3/multi/property/RENT/filter";

    public NoBrokerFlatRentScrapper() {

    }

    private static final class QueryParams {
        private final String page;
        private final String searchParam;
        private final String radius;

        public QueryParams(final String page,
                           final String searchParamHash,
                           final String radius) {
            this.page = page;
            this.searchParam = searchParamHash;
            this.radius = radius;
        }
    }
}
