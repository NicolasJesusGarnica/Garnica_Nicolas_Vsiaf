package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrganismoFinService {
    @Autowired private OrganismoFinRepository repository;
    public List<OrganismoFin> findAll() { return repository.findAll(); }
    public OrganismoFin save(OrganismoFin entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public OrganismoFin findById(Long id) { return repository.findById(id).orElse(null); }
}