package com.incubateur.carpoolconnect.services.impl;

import com.incubateur.carpoolconnect.entities.Role;
import com.incubateur.carpoolconnect.entities.User;
import com.incubateur.carpoolconnect.repositories.UserRepository;
import com.incubateur.carpoolconnect.utilities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final EmailServiceImpl emailService;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .dateOfBirth(LocalDate.parse(request.getDateOfBirth()))
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .activationKey(getKey())
                .isEnabled(false)
                .password(passwordEncoder.encode(request.getPassword()))
                .points(50)
                .role(Role.builder()
                        .name("ROLE_USER")
                        .build())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

        emailService.sendSimpleMessage(request.getEmail(), "Hey simple", "yo wat up");

        Context context = new Context();
        context.setVariable("firstName", user.getFirstName());
        context.setVariable("email", user.getEmail());
        context.setVariable("key", user.getActivationKey());
        emailService.sendEmailWithHtmlTemplate(user.getEmail(), "hey", "email-account-activation-template", context);


        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        if (user.isEnabled()) {
            var jwtToken = jwtService.generateToken((user));
            return AuthenticationResponse
                    .builder()
                    .token(jwtToken)
                    .build();
        }
        return null;
    }

    public boolean activateAccount(String email, String key) {
        var user = userRepository.findByEmail(email)
                .orElseThrow();
        if (user.getActivationKey().equals(key)) {
            user.setEnabled(true);
            userRepository.saveAndFlush(user);
        }
        return user.isEnabled();
    }

    public String reinitializePasswordEmail(String email) {
        var user = userRepository.findByEmail(email)
                .orElseThrow();

        String key = getKey();

        user.setPasswordRenewalKey(key);
        userRepository.saveAndFlush(user);

        Context context = new Context();
        context.setVariable("email", email);
        context.setVariable("key", key);
        emailService.sendEmailWithHtmlTemplate(user.getEmail(), "Réinitialisation mot de passe", "email-password-reinitialization", context);
        return "";
    }



    private String getKey() {
        String key = "";
        try {
            key = Base64.getEncoder().encodeToString(KeyGen.getKeyFromKeyGenerator(KeyGen.CIPHER, 112).getEncoded());
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return key;
    }
}
