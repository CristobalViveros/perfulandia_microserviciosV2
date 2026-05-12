package com.example.producto_ms.service;

import com.example.producto_ms.dto.ProductoDTO;
import com.example.producto_ms.exception.NotFoundException;
import com.example.producto_ms.model.Producto;
import com.example.producto_ms.repository.CategoriaRepository;
import com.example.producto_ms.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProductoDTO crear(ProductoDTO dto) {
        validarCategoriaExiste(dto.getCategoriaId());

        Producto p = mapToEntity(dto);
        Producto guardado = productoRepository.save(p);
        return mapToDTO(guardado);
    }

    @Override
    public List<ProductoDTO> listar() {
        return productoRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductoDTO obtenerPorId(Long id) {
        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + id));
        return mapToDTO(p);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        validarCategoriaExiste(dto.getCategoriaId());

        Producto p = productoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Producto no encontrado: " + id));

        p.setNombre(dto.getNombre().trim());
        p.setPrecio(dto.getPrecio());
        p.setCategoriaId(dto.getCategoriaId());

        return mapToDTO(productoRepository.save(p));
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)) {
            throw new NotFoundException("Producto no encontrado: " + id);
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<ProductoDTO> buscarPorNombre(String q) {
        return productoRepository.findByNombreContainingIgnoreCase(q).stream()
                .map(this::mapToDTO).collect(Collectors.toList());
    }

    private void validarCategoriaExiste(Long categoriaId) {
        if (!categoriaRepository.existsById(categoriaId)) {
            throw new NotFoundException("La categoría no existe: " + categoriaId);
        }
    }

    private ProductoDTO mapToDTO(Producto p) {
        return new ProductoDTO(p.getId(), p.getNombre(), p.getPrecio(), p.getCategoriaId());
    }

    private Producto mapToEntity(ProductoDTO dto) {
        Producto p = new Producto();
        p.setNombre(dto.getNombre().trim());
        p.setPrecio(dto.getPrecio());
        p.setCategoriaId(dto.getCategoriaId());
        return p;
    }
}