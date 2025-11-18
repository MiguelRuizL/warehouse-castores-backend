package com.example.warehouse_castores.exception;

import com.example.warehouse_castores.dto.GenericResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> HandleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error -> {
            errors.put(error.getField(), error.getDefaultMessage());
        });
        return errors;
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgument(IllegalArgumentException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GenericResponseDTO<Object>> handleBadCredentials(BadCredentialsException ex) {
        return new ResponseEntity<>(new GenericResponseDTO<>(
                false,
                "Credenciales inválidas. Verifique su usuario y contraseña.",
                null
        ), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GenericResponseDTO<String>> handleAccessDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(new GenericResponseDTO<>(
                false,
                "Acceso denegado.",
                ex.getMessage()
        ), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<GenericResponseDTO<String>> handleAuthorizationDenied(AccessDeniedException ex) {
        return new ResponseEntity<>(new GenericResponseDTO<>(
                false,
                "Permisos insuficientes.",
                "No tiene los permisos necesarios para realizar esta acción"
        ), HttpStatus.FORBIDDEN);
    }
}
