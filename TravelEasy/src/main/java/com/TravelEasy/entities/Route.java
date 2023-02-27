package com.TravelEasy.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String source;
    private String destination;
    @ManyToMany
    @JoinTable(
            name = "route_bus",
            joinColumns = @JoinColumn(name = "route_id"),
            inverseJoinColumns = @JoinColumn(name = "bus_id")
    )
    private List<Bus> buses;


    public void setBus(Bus bus) {
    }
}