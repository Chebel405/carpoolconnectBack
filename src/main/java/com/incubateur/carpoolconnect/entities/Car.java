package com.incubateur.carpoolconnect.entities;

import com.incubateur.carpoolconnect.audit.AbstractAuditable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cars")
public class Car extends AbstractAuditable implements Serializable {

    @Serial
    private static final long serialVersionUID = -1585279113086471460L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String brand;

    private String model;

    private String color;

    @OneToMany(mappedBy = "car")
    private List<Route> routes;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "car")
    private List<Picture> pictures;

}
