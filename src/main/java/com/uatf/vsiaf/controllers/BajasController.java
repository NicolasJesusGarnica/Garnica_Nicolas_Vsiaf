package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Bajas;
import com.uatf.vsiaf.services.BajasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/bajas-detalle")
public class BajasController {
    @Autowired private BajasService service;
    @GetMapping public List<Bajas> listar() { return service.findAll(); }
    @PostMapping public Bajas guardar(@RequestBody Bajas entity) { return service.save(entity); }
    @GetMapping("/{id}") public Bajas obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Bajas actualizar(@PathVariable Long id, @RequestBody Bajas entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}