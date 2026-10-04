package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Entidad;
import com.uatf.vsiaf.services.EntidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/entidades")
public class EntidadController {
    @Autowired private EntidadService service;
    @GetMapping public List<Entidad> listar() { return service.findAll(); }
    @PostMapping public Entidad guardar(@RequestBody Entidad entity) { return service.save(entity); }
    @GetMapping("/{id}") public Entidad obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Entidad actualizar(@PathVariable Long id, @RequestBody Entidad entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}