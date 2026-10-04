package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UnidadAdminService {
    @Autowired private UnidadAdminRepository repository;
    public List<UnidadAdmin> findAll() { return repository.findAll(); }
    public UnidadAdmin save(UnidadAdmin entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public UnidadAdmin findById(Long id) { return repository.findById(id).orElse(null); }
}