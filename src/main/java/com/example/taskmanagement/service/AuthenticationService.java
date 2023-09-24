package com.example.taskmanagement.service;

import com.example.taskmanagement.model.AuthenticationRequest;
import com.example.taskmanagement.model.AuthenticationResponse;
import com.example.taskmanagement.model.RegisterRequest;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);

    AuthenticationResponse authenticate(AuthenticationRequest request);
}
