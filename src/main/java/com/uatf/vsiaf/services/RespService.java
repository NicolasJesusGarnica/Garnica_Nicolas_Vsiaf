package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RespService {
    @Autowired private RespRepository repository;
    public List<Resp> findAll() { return repository.findAll(); }
    public Resp save(Resp entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Resp findById(Long id) { return repository.findById(id).orElse(null); }
}