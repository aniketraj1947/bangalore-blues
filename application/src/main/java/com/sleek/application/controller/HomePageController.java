package com.sleek.application.controller;

import com.sleek.application.service.OwnerService;
import com.sleek.application.service.OwnerServiceImpl;
import com.sleek.common.City;
import com.sleek.model.Owner;
import com.sleek.scrapper.nobroker.PropertyAttribute;
import com.sleek.scrapper.nobroker.clients.NoBrokerFlatRentScrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
public class HomePageController {

    @Autowired
    OwnerServiceImpl ownerService;

    @PostMapping("/localityAnalytics")
    public String processLocality(@RequestParam("selectedLocality") final String selectedLocality) throws Exception {
        final NoBrokerFlatRentScrapper scrapper = new NoBrokerFlatRentScrapper(City.BANGALORE.getString(), selectedLocality, 1);
        return "Response for" + selectedLocality + " : " +
                scrapper.getResponse().getResponseMap().stream().map(e -> e.get(PropertyAttribute.TITLE)).collect(Collectors.toList());
    }
}
