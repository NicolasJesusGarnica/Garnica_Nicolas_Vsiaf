package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.EstEnt;
import com.uatf.vsiaf.services.EstEntService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/est-ent")
public class EstEntController {
    @Autowired private EstEntService service;
    @GetMapping public List<EstEnt> listar() { return service.findAll(); }
    @PostMapping public EstEnt guardar(@RequestBody EstEnt entity) { return service.save(entity); }
    @GetMapping("/{id}") public EstEnt obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public EstEnt actualizar(@PathVariable Long id, @RequestBody EstEnt entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}