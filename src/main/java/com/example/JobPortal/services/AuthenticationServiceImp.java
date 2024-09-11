package com.example.JobPortal.services;

import com.example.JobPortal.models.AuthenticationResponse;
import com.example.JobPortal.models.Users;
import com.example.JobPortal.repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImp {
    @Autowired
    private UsersRepo repository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(Users request) {
        Users user = new Users();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());
        repository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);


    }

    public AuthenticationResponse authenticate(Users request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( request.getEmail(), request.getPassword()));
        Users user=repository.findByEmail(request.getEmail());
        String token=jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
