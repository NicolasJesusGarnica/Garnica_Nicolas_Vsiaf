package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstEntService {
    @Autowired private EstEntRepository repository;
    public List<EstEnt> findAll() { return repository.findAll(); }
    public EstEnt save(EstEnt entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public EstEnt findById(Long id) { return repository.findById(id).orElse(null); }
}