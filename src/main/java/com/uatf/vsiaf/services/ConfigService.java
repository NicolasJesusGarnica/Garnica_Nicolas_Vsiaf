package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConfigService {
    @Autowired private ConfigRepository repository;
    public List<Config> findAll() { return repository.findAll(); }
    public Config save(Config entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public Config findById(Long id) { return repository.findById(id).orElse(null); }
}