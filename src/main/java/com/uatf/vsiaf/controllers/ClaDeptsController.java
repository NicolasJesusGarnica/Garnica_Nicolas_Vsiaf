package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.ClaDepts;
import com.uatf.vsiaf.services.ClaDeptsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cla-depts")
public class ClaDeptsController {
    @Autowired private ClaDeptsService service;
    @GetMapping public List<ClaDepts> listar() { return service.findAll(); }
    @PostMapping public ClaDepts guardar(@RequestBody ClaDepts entity) { return service.save(entity); }
    @GetMapping("/{id}") public ClaDepts obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public ClaDepts actualizar(@PathVariable Long id, @RequestBody ClaDepts entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}