package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BajaService {
    @Autowired private BajaRepository repository;
    public List<Baja> findAll() { return repository.findAll(); }
    public Baja save(Baja entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Baja findById(Long id) { return repository.findById(id).orElse(null); }
}