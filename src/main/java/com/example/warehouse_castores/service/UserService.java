package com.example.warehouse_castores.service;

import com.example.warehouse_castores.dto.auth.CreateUserDTO;
import com.example.warehouse_castores.model.Role;
import com.example.warehouse_castores.model.User;
import com.example.warehouse_castores.repository.RoleRepository;
import com.example.warehouse_castores.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
}
