package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Orguser;
import com.uatf.vsiaf.services.OrguserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orguser")
public class OrguserController {
    @Autowired private OrguserService service;
    @GetMapping public List<Orguser> listar() { return service.findAll(); }
    @PostMapping public Orguser guardar(@RequestBody Orguser entity) { return service.save(entity); }
    @GetMapping("/{id}") public Orguser obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Orguser actualizar(@PathVariable Long id, @RequestBody Orguser entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}