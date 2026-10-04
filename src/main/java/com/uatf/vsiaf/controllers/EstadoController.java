package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Estado;
import com.uatf.vsiaf.services.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {
    @Autowired private EstadoService service;
    @GetMapping public List<Estado> listar() { return service.findAll(); }
    @PostMapping public Estado guardar(@RequestBody Estado entity) { return service.save(entity); }
    @GetMapping("/{id}") public Estado obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Estado actualizar(@PathVariable Long id, @RequestBody Estado entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}