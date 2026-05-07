package com.example.producto_ms.controller;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @PostMapping
    public ProductoDTO crear(@RequestBody ProductoDTO dto) {
        return productoService.crear(dto);
    }
}