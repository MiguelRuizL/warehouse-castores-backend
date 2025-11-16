package com.example.warehouse_castores.dto.auth;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long id;
    private String username;
    private String email;
    private String roleName;
    private Boolean status;
}
