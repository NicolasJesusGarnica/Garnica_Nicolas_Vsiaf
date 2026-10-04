package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstadoService {
    @Autowired private EstadoRepository repository;
    public List<Estado> findAll() { return repository.findAll(); }
    public Estado save(Estado entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Estado findById(Long id) { return repository.findById(id).orElse(null); }
}