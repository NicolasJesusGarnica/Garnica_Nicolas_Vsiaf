package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Auxiliar;
import com.uatf.vsiaf.services.AuxiliarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/auxiliares")
public class AuxiliarController {
    @Autowired private AuxiliarService service;
    @GetMapping public List<Auxiliar> listar() { return service.findAll(); }
    @PostMapping public Auxiliar guardar(@RequestBody Auxiliar entity) { return service.save(entity); }
    @GetMapping("/{id}") public Auxiliar obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Auxiliar actualizar(@PathVariable Long id, @RequestBody Auxiliar entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}