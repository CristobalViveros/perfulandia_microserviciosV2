package com.example.producto_ms.controller;

import com.example.producto_ms.dto.ProductoDTO;
import com.example.producto_ms.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@Validated
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public List<ProductoDTO> listar() {
        return productoService.listar();
    }

    @GetMapping("/{id}")
    public ProductoDTO obtener(@PathVariable @Positive(message = "id debe ser positivo") Long id) {
        return productoService.obtenerPorId(id);
    }

    @PostMapping
    public ProductoDTO crear(@Valid @RequestBody ProductoDTO dto) {
        return productoService.crear(dto);
    }

    @PutMapping("/{id}")
    public ProductoDTO actualizar(@PathVariable @Positive(message = "id debe ser positivo") Long id,
                                  @Valid @RequestBody ProductoDTO dto) {
        return productoService.actualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable @Positive(message = "id debe ser positivo") Long id) {
        productoService.eliminar(id);
    }

    @GetMapping("/buscar")
    public List<ProductoDTO> buscar(@RequestParam("q") @NotBlank(message = "q no puede estar vacío") String q) {
        return productoService.buscarPorNombre(q);
    }
}