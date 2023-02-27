package com.TravelEasy.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import sun.security.krb5.internal.Ticket;

import javax.persistence.*;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="buses")
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="names")
    private String name;
    @Column(name="capacities")
    private int capacity;
    @ManyToMany(mappedBy = "buses")
    private List<Route> routes;


}
