package com.example.producto_ms.service;

public interface ProductoService {
    ProductoDTO crear(ProductoDTO dto);
    List<ProductoDTO> listar();
}