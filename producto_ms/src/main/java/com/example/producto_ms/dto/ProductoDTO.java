package com.example.producto_ms.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Long id;

    @NotBlank(message = "El nombre del producto es obligatorio")
    @Size(min = 2, max = 80, message = "El nombre debe tener entre 2 y 80 caracteres")
    private String nombre;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 12, fraction = 2, message = "El precio debe tener máximo 12 enteros y 2 decimales")
    private BigDecimal precio;

    @NotNull(message = "categoriaId es obligatorio")
    @Positive(message = "categoriaId debe ser un número positivo")
    private Long categoriaId;
}