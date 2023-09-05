package com.sleek;

import java.io.IOException;

public abstract class Scrapper {
    protected String url;

    public abstract String getResponseForGETRequest() throws IOException;
    public abstract String getResponseForPOSTRequest() throws IOException;
    public abstract String getResponseForPUTRequest() throws IOException;
}