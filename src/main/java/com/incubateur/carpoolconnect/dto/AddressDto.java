package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {

    private int number;

    private String street;

    private float latitude;

    private float longitude;

    private int zipcode;
}
