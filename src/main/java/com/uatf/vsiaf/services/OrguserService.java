package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrguserService {
    @Autowired private OrguserRepository repository;
    public List<Orguser> findAll() { return repository.findAll(); }
    public Orguser save(Orguser entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Orguser findById(Long id) { return repository.findById(id).orElse(null); }
}