package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Trasfe;
import com.uatf.vsiaf.services.TrasfeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trasfe")
public class TrasfeController {
    @Autowired private TrasfeService service;
    @GetMapping public List<Trasfe> listar() { return service.findAll(); }
    @PostMapping public Trasfe guardar(@RequestBody Trasfe entity) { return service.save(entity); }
    @GetMapping("/{id}") public Trasfe obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Trasfe actualizar(@PathVariable Long id, @RequestBody Trasfe entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}