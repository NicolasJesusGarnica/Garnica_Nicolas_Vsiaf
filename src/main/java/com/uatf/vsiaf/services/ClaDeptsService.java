package com.uatf.vsiaf.services;
import com.uatf.vsiaf.entities.*;
import com.uatf.vsiaf.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClaDeptsService {
    @Autowired private ClaDeptsRepository repository;
    public List<ClaDepts> findAll() { return repository.findAll(); }
    public ClaDepts save(ClaDepts entity) { return repository.save(entity); }
    public void deleteById(Long id) { repository.deleteById(id); }
    public ClaDepts findById(Long id) { return repository.findById(id).orElse(null); }
}