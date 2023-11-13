package com.incubateur.carpoolconnect.dto;

import com.incubateur.carpoolconnect.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {

    private RouteDto route;

    private UserDto user;

    private ReservationStatus status;
}
