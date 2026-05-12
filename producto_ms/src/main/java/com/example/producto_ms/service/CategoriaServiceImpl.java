package com.example.producto_ms.service;

import com.example.producto_ms.dto.CategoriaDTO;
import com.example.producto_ms.exception.BusinessException;
import com.example.producto_ms.exception.NotFoundException;
import com.example.producto_ms.model.Categoria;
import com.example.producto_ms.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaDTO crear(CategoriaDTO dto) {
        if (categoriaRepository.existsByNombreIgnoreCase(dto.getNombre())) {
            throw new BusinessException("Ya existe una categoría con ese nombre");
        }
        Categoria c = new Categoria();
        c.setNombre(dto.getNombre().trim());
        return mapToDTO(categoriaRepository.save(c));
    }

    @Override
    public List<CategoriaDTO> listar() {
        return categoriaRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CategoriaDTO obtenerPorId(Long id) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));
        return mapToDTO(c);
    }

    @Override
    public CategoriaDTO actualizar(Long id, CategoriaDTO dto) {
        Categoria c = categoriaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Categoría no encontrada: " + id));

        String nuevoNombre = dto.getNombre().trim();
        if (!c.getNombre().equalsIgnoreCase(nuevoNombre)
                && categoriaRepository.existsByNombreIgnoreCase(nuevoNombre)) {
            throw new BusinessException("Ya existe una categoría con ese nombre");
        }

        c.setNombre(nuevoNombre);
        return mapToDTO(categoriaRepository.save(c));
    }

    @Override
    public void eliminar(Long id) {
        if (!categoriaRepository.existsById(id)) {
            throw new NotFoundException("Categoría no encontrada: " + id);
        }
        categoriaRepository.deleteById(id);
    }

    private CategoriaDTO mapToDTO(Categoria c) {
        return new CategoriaDTO(c.getId(), c.getNombre());
    }
}
