package com.sleek.model;

import jakarta.persistence.*;

@Entity
@Table(name = "locality")
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "localityId")
    private long localityId;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "distanceToAirport")
    private int distanceToAirport;

    @Column(name = "distanceToRailways")
    private int distanceToRailways;
}
