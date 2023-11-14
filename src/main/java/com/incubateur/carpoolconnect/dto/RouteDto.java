package com.incubateur.carpoolconnect.dto;

import com.incubateur.carpoolconnect.entities.Address;
import com.incubateur.carpoolconnect.entities.Car;
import com.incubateur.carpoolconnect.entities.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDto {

    private LocalDateTime departureDate;

    private int seats;

    private int smallBaggage;

    private int largeBaggage;

    private boolean silence;

    private String musicGenre;

    private UserDto driver;

    private CarDto car;

    private AddressDto departure;

    private AddressDto destination;

    private List<AddressDto> intermediateAddresses;

    private List<RatingDto> ratings;

    private List<ReservationDto> reservations;


}
