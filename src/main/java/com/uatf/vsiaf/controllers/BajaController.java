package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Baja;
import com.uatf.vsiaf.services.BajaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/baja")
public class BajaController {
    @Autowired private BajaService service;
    @GetMapping public List<Baja> listar() { return service.findAll(); }
    @PostMapping public Baja guardar(@RequestBody Baja entity) { return service.save(entity); }
    @GetMapping("/{id}") public Baja obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Baja actualizar(@PathVariable Long id, @RequestBody Baja entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}