package com.example.warehouse_castores.controller;

import com.example.warehouse_castores.dto.GenericResponseDTO;
import com.example.warehouse_castores.dto.auth.UserResponseDTO;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public ResponseEntity<GenericResponseDTO<UserResponseDTO>> getCurrentUser() {
        UserResponseDTO userProfile = userService.getCurrentUser();
        return new ResponseEntity<>(new GenericResponseDTO<>(
                true,
                "Perfil de usuario obtenido",
                userProfile
        ), HttpStatus.OK);
    }
}
