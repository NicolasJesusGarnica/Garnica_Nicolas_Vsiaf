package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Backups;
import com.uatf.vsiaf.services.BackupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/backups")
public class BackupsController {
    @Autowired private BackupsService service;
    @GetMapping public List<Backups> listar() { return service.findAll(); }
    @PostMapping public Backups guardar(@RequestBody Backups entity) { return service.save(entity); }
    @GetMapping("/{id}") public Backups obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Backups actualizar(@PathVariable Long id, @RequestBody Backups entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}