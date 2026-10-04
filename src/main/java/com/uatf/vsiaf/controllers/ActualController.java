package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Actual;
import com.uatf.vsiaf.services.ActualService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/actual")
public class ActualController {
    @Autowired private ActualService service;
    @GetMapping public List<Actual> listar() { return service.findAll(); }
    @PostMapping public Actual guardar(@RequestBody Actual entity) { return service.save(entity); }
    @GetMapping("/{id}") public Actual obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Actual actualizar(@PathVariable Long id, @RequestBody Actual entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}