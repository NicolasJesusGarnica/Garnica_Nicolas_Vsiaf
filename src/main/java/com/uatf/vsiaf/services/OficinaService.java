package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OficinaService {
    @Autowired private OficinaRepository repository;
    public List<Oficina> findAll() { return repository.findAll(); }
    public Oficina save(Oficina entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Oficina findById(Long id) { return repository.findById(id).orElse(null); }
}