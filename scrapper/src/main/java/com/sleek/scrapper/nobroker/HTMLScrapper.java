package com.sleek.scrapper.nobroker;

import java.io.IOException;

public class HTMLScrapper extends Scrapper {
    @Override
    public String getResponseForGETRequest() throws IOException {
        throw new UnsupportedOperationException("HTML based scrapping not implemented");
    }

    @Override
    public String getResponseForPOSTRequest() throws IOException {
        throw new UnsupportedOperationException("HTML based scrapping not implemented");
    }

    @Override
    public String getResponseForPUTRequest() throws IOException {
        throw new UnsupportedOperationException("HTML based scrapping not implemented");
    }
}
