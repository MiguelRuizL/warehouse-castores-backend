package com.example.warehouse_castores.dto.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class UserResponseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;
    private String username;
    private String email;
    private String roleName;
    private Boolean status;
}
