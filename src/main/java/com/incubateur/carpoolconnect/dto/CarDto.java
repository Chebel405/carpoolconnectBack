package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private String brand;

    private String model;

    private String color;

    private List<RouteDto> routes;

    private UserDto user;

    private List<PictureDto> pictures;
}
