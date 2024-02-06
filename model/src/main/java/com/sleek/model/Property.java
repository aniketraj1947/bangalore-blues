package com.sleek.model;

import jakarta.persistence.*;

@Entity
public class Property {
    @Id
    private long propertyId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ownerId")
    private Owner owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "localityId")
    private Locality locality;

    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private String location;

    @Column(name = "propertyType")
    private String propertyType;

    @Column(name = "monthlyRentAmount")
    private int monthlyRentAmount;

    @Column(name = "depositAmount")
    private int depositAmount;

    @Column(name = "floor")
    private int floor;

    @Column(name = "propertyScore")
    private int propertyScore;
}