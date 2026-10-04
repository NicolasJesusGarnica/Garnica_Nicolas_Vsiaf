package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DataService {
    @Autowired private DataRepository repository;
    public List<Data> findAll() { return repository.findAll(); }
    public Data save(Data entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Data findById(Long id) { return repository.findById(id).orElse(null); }
}