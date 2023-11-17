package com.incubateur.carpoolconnect.utilities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PasswordRenewalRequest {

    private String password;
    private String key;
}
