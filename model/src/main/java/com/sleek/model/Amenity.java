package com.sleek.model;

import jakarta.persistence.*;

@Entity
@Table(name = "amentity")
public class Amenity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "amenityId")
    private long amenityId;
    @Column(name = "name")
    private String name;
    @Column(name="description")
    private String description;
}
