package com.example.warehouse_castores.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {
    @Size(max = 255, message = "El nombre del producto no debe exceder los 255 caracteres")
    private String name;

    private String type; // input/output

    // cantidad para hacer suma/resta
    private Integer toSumQuantity;

    // cantidad para sobreescribir totalmente (no debe sser negativa)
    @Min(0)
    private Integer fullNewQuantity;

    private Boolean status;
}
