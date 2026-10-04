package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CtaParService {
    @Autowired private CtaParRepository repository;
    public List<CtaPar> findAll() { return repository.findAll(); }
    public CtaPar save(CtaPar entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public CtaPar findById(Long id) { return repository.findById(id).orElse(null); }
}