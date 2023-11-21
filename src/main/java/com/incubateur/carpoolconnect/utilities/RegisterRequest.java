package com.incubateur.carpoolconnect.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String lastName;
    private String firstName;
    private String dateOfBirth;
    private String email;
    private String password;
    private String phoneNumber;


}
