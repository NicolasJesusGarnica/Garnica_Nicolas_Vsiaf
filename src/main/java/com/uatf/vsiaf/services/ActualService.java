package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActualService {
    @Autowired private ActualRepository repository;
    public List<Actual> findAll() { return repository.findAll(); }
    public Actual save(Actual entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Actual findById(Long id) { return repository.findById(id).orElse(null); }
}