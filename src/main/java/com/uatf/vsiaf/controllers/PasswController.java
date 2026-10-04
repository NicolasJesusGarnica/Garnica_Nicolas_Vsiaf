package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Passw;
import com.uatf.vsiaf.services.PasswService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/passw")
public class PasswController {
    @Autowired private PasswService service;
    @GetMapping public List<Passw> listar() { return service.findAll(); }
    @PostMapping public Passw guardar(@RequestBody Passw entity) { return service.save(entity); }
    @GetMapping("/{id}") public Passw obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Passw actualizar(@PathVariable Long id, @RequestBody Passw entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}