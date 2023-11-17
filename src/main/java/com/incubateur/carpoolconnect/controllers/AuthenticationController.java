package com.incubateur.carpoolconnect.controllers;

import com.incubateur.carpoolconnect.services.impl.AuthenticationService;
import com.incubateur.carpoolconnect.utilities.AuthenticationRequest;
import com.incubateur.carpoolconnect.utilities.AuthenticationResponse;
import com.incubateur.carpoolconnect.utilities.RegisterRequest;
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
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin(origins = "*")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping(value = "/activate/{email}/{key}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<Boolean> activateAccount(@PathVariable String email, @PathVariable String key) {
        return ResponseEntity.ok(service.activateAccount(email, key));
    }

    @PostMapping(value = "/password/email/{email}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> reinitializePassword(@PathVariable String email) {
        return ResponseEntity.ok(service.reinitializePasswordEmail(email));
    }
}
