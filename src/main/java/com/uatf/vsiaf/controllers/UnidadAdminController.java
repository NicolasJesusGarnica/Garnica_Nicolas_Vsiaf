package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.UnidadAdmin;
import com.uatf.vsiaf.services.UnidadAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/unidadadmin")
public class UnidadAdminController {
    @Autowired private UnidadAdminService service;

    @GetMapping
    public List<UnidadAdmin> listar() { return service.findAll(); }

    @PostMapping
    public UnidadAdmin guardar(@RequestBody UnidadAdmin entity) { return service.save(entity); }

    @GetMapping("/{id}")
    public UnidadAdmin obtenerPorId(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}")
    public UnidadAdmin actualizar(@PathVariable Long id, @RequestBody UnidadAdmin entity) {
        entity.setId(id);
        return service.save(entity);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.deleteById(id);
    }
}