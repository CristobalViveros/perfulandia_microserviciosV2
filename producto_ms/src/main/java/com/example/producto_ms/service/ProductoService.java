package com.example.producto_ms.service;

import com.example.producto_ms.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {
    ProductoDTO crear(ProductoDTO dto);
    List<ProductoDTO> listar();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO actualizar(Long id, ProductoDTO dto);
    void eliminar(Long id);

    List<ProductoDTO> buscarPorNombre(String q);
}