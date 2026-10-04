package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MesService {
    @Autowired private MesRepository repository;
    public List<Mes> findAll() { return repository.findAll(); }
    public Mes save(Mes entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Mes findById(Long id) { return repository.findById(id).orElse(null); }
}