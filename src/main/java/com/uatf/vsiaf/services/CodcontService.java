package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CodcontService {
    @Autowired private CodcontRepository repository;
    public List<Codcont> findAll() { return repository.findAll(); }
    public Codcont save(Codcont entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Codcont findById(Long id) { return repository.findById(id).orElse(null); }
}