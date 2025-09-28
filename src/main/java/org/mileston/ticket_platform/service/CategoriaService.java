package org.mileston.ticket_platform.service;

import java.util.List;
import java.util.Optional;

import org.mileston.ticket_platform.model.Categoria;
import org.mileston.ticket_platform.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

     public Optional<Categoria> findById(Long id) {
        return categoriaRepository.findById(id);
    }

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void deleteById(Long id) {
        categoriaRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return categoriaRepository.existsById(id);
    }  

}
