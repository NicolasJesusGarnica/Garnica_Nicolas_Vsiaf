package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.services.CodcontService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/codcont")
public class CodcontController {
    @Autowired private CodcontService service;
    @GetMapping public List<Codcont> listar() { return service.findAll(); }
    @PostMapping public Codcont guardar(@RequestBody Codcont entity) { return service.save(entity); }
    @GetMapping("/{id}") public Codcont obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Codcont actualizar(@PathVariable Long id, @RequestBody Codcont entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}