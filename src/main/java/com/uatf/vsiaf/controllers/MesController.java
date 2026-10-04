package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Mes;
import com.uatf.vsiaf.services.MesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/mes")
public class MesController {
    @Autowired private MesService service;
    @GetMapping public List<Mes> listar() { return service.findAll(); }
    @PostMapping public Mes guardar(@RequestBody Mes entity) { return service.save(entity); }
    @GetMapping("/{id}") public Mes obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Mes actualizar(@PathVariable Long id, @RequestBody Mes entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}