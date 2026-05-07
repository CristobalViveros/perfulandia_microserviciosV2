package com.example.producto_ms.model;

@Entity
public class Producto {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private BigDecimal precio;
    private Long categoriaId;
}
