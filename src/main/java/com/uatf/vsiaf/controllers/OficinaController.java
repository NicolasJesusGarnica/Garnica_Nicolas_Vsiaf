package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Oficina;
import com.uatf.vsiaf.services.OficinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/oficinas")
public class OficinaController {
    @Autowired private OficinaService service;
    @GetMapping public List<Oficina> listar() { return service.findAll(); }
    @PostMapping public Oficina guardar(@RequestBody Oficina entity) { return service.save(entity); }
    @GetMapping("/{id}") public Oficina obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Oficina actualizar(@PathVariable Long id, @RequestBody Oficina entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}