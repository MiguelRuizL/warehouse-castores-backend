package com.example.warehouse_castores.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@Entity
public class Logbook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="movement_type", columnDefinition = "varchar(20)")
    private String movementType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_product")
    private Product product;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_user")
    private User user;

    private Integer quantity;

    @Column(name = "done_at")
    private LocalDateTime doneAt;
}
