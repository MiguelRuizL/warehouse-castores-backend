package com.example.warehouse_castores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username", columnDefinition="nvarchar(100)")
    private String username;

    @Column(columnDefinition="nvarchar(100)")
    private String email;

    @Column(columnDefinition="nvarchar(max)")
    private String password;

    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_role")
    private Role role;

    private Boolean status;

    public String getRealUsername() {
        return this.username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // lista rol del usuario
        return List.of(new SimpleGrantedAuthority(role.getName()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isEnabled() {
        return this.status;
    }
}
