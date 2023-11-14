package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private int number;

    private String street;

    private String city;

    private float latitude;

    private float longitude;

    private int zipcode;

    private List<RouteDto> routeDepart;

    private List<RouteDto> routeDestination;

    private List<RouteDto> routes;
}
