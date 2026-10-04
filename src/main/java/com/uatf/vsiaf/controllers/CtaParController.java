package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.CtaPar;
import com.uatf.vsiaf.services.CtaParService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cta-par")
public class CtaParController {
    @Autowired private CtaParService service;
    @GetMapping public List<CtaPar> listar() { return service.findAll(); }
    @PostMapping public CtaPar guardar(@RequestBody CtaPar entity) { return service.save(entity); }
    @GetMapping("/{id}") public CtaPar obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public CtaPar actualizar(@PathVariable Long id, @RequestBody CtaPar entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}