package com.example.warehouse_castores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition="nvarchar(100)")
    private String username;

    @Column(columnDefinition="nvarchar(100)")
    private String email;

    @Column(columnDefinition="nvarchar(max)")
    private String password;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="id_role")
    private Role role;

    private Boolean status;
}
