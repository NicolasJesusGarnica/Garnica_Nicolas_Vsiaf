package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RevalService {
    @Autowired private RevalRepository repository;
    public List<Reval> findAll() { return repository.findAll(); }
    public Reval save(Reval entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Reval findById(Long id) { return repository.findById(id).orElse(null); }
}