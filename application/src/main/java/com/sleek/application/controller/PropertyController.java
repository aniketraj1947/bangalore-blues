package com.sleek.application.controller;

import com.sleek.application.service.OwnerService;
import com.sleek.model.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PropertyController {

    @Autowired
    private OwnerService ownerService;

    @GetMapping("/property")
    public String getOwner(@RequestParam(value = "id") final String id) {
        return ownerService.getOwnerById(id).toString();
    }

    @PostMapping("/property")
    public String postOwner(final Owner owner) {
        ownerService.save(owner);
        return "Saved owner: " + owner;
    }
}
