package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BajasService {
    @Autowired private BajasRepository repository;
    public List<Bajas> findAll() { return repository.findAll(); }
    public Bajas save(Bajas entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Bajas findById(Long id) { return repository.findById(id).orElse(null); }
}