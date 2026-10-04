package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TrasfeService {
    @Autowired private TrasfeRepository repository;
    public List<Trasfe> findAll() { return repository.findAll(); }
    public Trasfe save(Trasfe entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Trasfe findById(Long id) { return repository.findById(id).orElse(null); }
}