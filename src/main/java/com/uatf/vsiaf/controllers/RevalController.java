package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Reval;
import com.uatf.vsiaf.services.RevalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reval")
public class RevalController {
    @Autowired private RevalService service;
    @GetMapping public List<Reval> listar() { return service.findAll(); }
    @PostMapping public Reval guardar(@RequestBody Reval entity) { return service.save(entity); }
    @GetMapping("/{id}") public Reval obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Reval actualizar(@PathVariable Long id, @RequestBody Reval entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}