package com.example.warehouse_castores.service;

import com.example.warehouse_castores.dto.auth.CreateUserDTO;
import com.example.warehouse_castores.dto.auth.UserResponseDTO;
import com.example.warehouse_castores.model.Role;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.repository.RoleRepository;
import com.example.warehouse_castores.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    @Lazy
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));
    }

    @Transactional
    public User createUser(CreateUserDTO userData) {
        // validaciones
        if (userRepository.existsByUsername(userData.getUsername()))
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        if (userRepository.existsByEmail(userData.getEmail()))
            throw new IllegalArgumentException("El email ya está en uso.");
        Role userRole = roleRepository.findById(userData.getRoleId())
                .orElseThrow(() -> new RuntimeException("Rol no encontrado."));

        // nuevo usuario
        User newUser = new User();
        newUser.setUsername(userData.getUsername());
        newUser.setEmail(userData.getEmail());
        newUser.setPassword(passwordEncoder.encode(userData.getPassword()));
        newUser.setRole(userRole);
        newUser.setStatus(true);

        return userRepository.save(newUser);
    }

    public User getAuthenticatedUserEntity() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || authentication instanceof AnonymousAuthenticationToken) {
            throw new UsernameNotFoundException("No hay un usuario autenticado");
        }
        return (User) authentication.getPrincipal();
    }

    public Role getCurrentUserRole() {
        return getAuthenticatedUserEntity().getRole();
    }

    // para devolver una respuesta JSON segura (sin contraseña del usuario / ya no se usa)
    public UserResponseDTO getCurrentUser() throws UsernameNotFoundException {
            User currentUser = getAuthenticatedUserEntity();
            UserResponseDTO response = new UserResponseDTO();
            response.setUsername(currentUser.getRealUsername());
            response.setEmail(currentUser.getEmail());
            response.setRoleName(currentUser.getRole().getName());
            response.setStatus(currentUser.getStatus());

            return response;
    }
}
