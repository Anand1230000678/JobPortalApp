package com.example.JobPortal.controller;

import com.example.JobPortal.models.AuthenticationResponse;
import com.example.JobPortal.models.Users;
import com.example.JobPortal.services.AuthenticationServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationServiceImp authService;




    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Users request
    ) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody Users request ) {
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
