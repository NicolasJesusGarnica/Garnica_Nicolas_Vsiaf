package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReportsService {
    @Autowired private ReportsRepository repository;
    public List<Reports> findAll() { return repository.findAll(); }
    public Reports save(Reports entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Reports findById(Long id) { return repository.findById(id).orElse(null); }
}