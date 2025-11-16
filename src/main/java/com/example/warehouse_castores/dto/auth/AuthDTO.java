package com.example.warehouse_castores.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthDTO {
    @NotEmpty(message = "Ingrese un nombre de usuario")
    private String username;
    @NotEmpty(message = "Ingrese contraseña")
    private String password;
}
