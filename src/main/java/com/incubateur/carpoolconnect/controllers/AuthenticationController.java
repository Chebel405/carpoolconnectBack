package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.services.impl.AuthenticationService;
import com.incubateur.carpoolconnect.utilities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<Boolean> activateAccount(@RequestBody ActivationRequest request) {
        System.out.println("controller");
        return ResponseEntity.ok(service.activateAccount(request));
    }

    @PostMapping(value = "/password/email")
    public ResponseEntity<String> reinitializePasswordEmail(@RequestBody PasswordEmailRequest request) {
        return ResponseEntity.ok(service.reinitializePasswordEmail(request));
    }

    @PostMapping(value = "/password")
    public ResponseEntity<String> reinitializePassword(@RequestBody PasswordRenewalRequest request) {
        return ResponseEntity.ok(service.reinitializePassword(request));
    }
}
