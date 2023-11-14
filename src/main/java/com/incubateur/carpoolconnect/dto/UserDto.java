package com.incubateur.carpoolconnect.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate dateOfBirth;

    private int phoneNumber;

    private int points;

    private List<ChatMessageDto> messagesSent;

    private List<ChatMessageDto> messagesReceived;

    private List<CommentDto> comments;

    private List<RouteDto> routesProposed;

    private List<RatingDto> ratings;

    private List<CarDto> cars;

    private List<PictureDto> icons;

    private List<ReservationDto> reservations;

    private RoleDto role;

}
