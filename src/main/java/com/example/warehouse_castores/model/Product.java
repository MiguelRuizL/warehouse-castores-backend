package com.example.warehouse_castores.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre del producto no puede estar vacío.")
    @Size(max = 255, message = "El nombre del producto no debe exceder los 255 caracteres")
    private String name;

    private Integer quantity;

    private Boolean status;

    @PrePersist
    public void setDefaultValuesOnCreate() {
        if (this.quantity == null) this.quantity = 0;
        if (this.status == null) this.status = true;
    }
}
