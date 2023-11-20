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

import java.time.LocalDate;
import java.util.UUID;

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
                .isEnabled(true)
                .password(passwordEncoder.encode(request.getPassword()))
                .points(50)
                .role(Role.builder()
                        .name("ROLE_USER")
                        .build())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);

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
        if (verifyActivationKey(key, user)) {
            user.setEnabled(true);
            user.setActivationKey(null);
            userRepository.saveAndFlush(user);
        }
        return user.isEnabled();
    }

    public String reinitializePasswordEmail(PasswordEmailRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String key = getKey();

        user.setPasswordRenewalKey(key);
        userRepository.saveAndFlush(user);

        Context context = new Context();
        context.setVariable("email", request.getEmail());
        context.setVariable("key", key);
        emailService.sendEmailWithHtmlTemplate(user.getEmail(), "Réinitialisation mot de passe", "email-password-reinitialization", context);
        return "";
    }

    public String reinitializePassword(PasswordRenewalRequest request) {
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();
        if (verifyPasswordKey(request.getKey(), user)) {
            user.setPassword(passwordEncoder.encode(request.getPassword()));
            user.setPasswordRenewalKey(null);
            userRepository.saveAndFlush(user);
            return "";
        }
        return null;
    }

    private String getKey() {
        return UUID.randomUUID().toString();
    }

    private boolean verifyActivationKey(String key, User user) {
        return key.equals(user.getActivationKey());
    }

    private boolean verifyPasswordKey(String key, User user) {
        return key.equals(user.getPasswordRenewalKey());
    }

}
