package com.example.warehouse_castores.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUserDTO {
    @NotEmpty(message = "Ingrese un nombre de usuario")
    private String username;

    @NotEmpty(message = "Ingrese un correo")
    @Email(message = "Formato de correo electrónico inválido")
    private String email;

    @NotEmpty(message = "La contraseña no puede estar vacía")
    private String password;

    @NotNull(message = "El id del rol no puede ser nulo")
    private Long roleId;
}
