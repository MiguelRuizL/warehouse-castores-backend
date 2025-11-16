package com.example.warehouse_castores.controller;

import com.example.warehouse_castores.dto.auth.AuthDTO;
import com.example.warehouse_castores.dto.GenericResponseDTO;
import com.example.warehouse_castores.dto.auth.CreateUserDTO;
import com.example.warehouse_castores.dto.auth.UserResponseDTO;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.service.JwtService;
import com.example.warehouse_castores.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<GenericResponseDTO<String>> login(@Valid @RequestBody AuthDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        final UserDetails userDetails = userService.loadUserByUsername(request.getUsername());
        final String jwt = jwtService.generateToken(userDetails);

        return new ResponseEntity<>(new GenericResponseDTO<>(
                true,
                "Inicio de sesión realizado correctamente.",
                jwt
        ), HttpStatus.CREATED);
    }

    @PostMapping("/register")
    public ResponseEntity<GenericResponseDTO<Object>> registerUser(
            @Valid @RequestBody CreateUserDTO userData
    ) {
        User newUser = userService.createUser(userData);
        return new ResponseEntity<>(new GenericResponseDTO<>(
                true,
                "Usuario registrado exitosamente. Por favor, inice sesión.",
                null
        ), HttpStatus.CREATED);
    }
}
