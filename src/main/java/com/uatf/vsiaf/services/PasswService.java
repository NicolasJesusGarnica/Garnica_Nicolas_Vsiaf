package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PasswService {
    @Autowired private PasswRepository repository;
    public List<Passw> findAll() { return repository.findAll(); }
    public Passw save(Passw entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Passw findById(Long id) { return repository.findById(id).orElse(null); }
}