package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Objgasto;
import com.uatf.vsiaf.services.ObjgastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/objgasto")
public class ObjgastoController {
    @Autowired private ObjgastoService service;
    @GetMapping public List<Objgasto> listar() { return service.findAll(); }
    @PostMapping public Objgasto guardar(@RequestBody Objgasto entity) { return service.save(entity); }
    @GetMapping("/{id}") public Objgasto obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Objgasto actualizar(@PathVariable Long id, @RequestBody Objgasto entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}