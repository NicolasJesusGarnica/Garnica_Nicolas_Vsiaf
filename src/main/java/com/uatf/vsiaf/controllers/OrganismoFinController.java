package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.OrganismoFin;
import com.uatf.vsiaf.services.OrganismoFinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/organismo-fin")
public class OrganismoFinController {
    @Autowired private OrganismoFinService service;
    @GetMapping public List<OrganismoFin> listar() { return service.findAll(); }
    @PostMapping public OrganismoFin guardar(@RequestBody OrganismoFin entity) { return service.save(entity); }
    @GetMapping("/{id}") public OrganismoFin obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public OrganismoFin actualizar(@PathVariable Long id, @RequestBody OrganismoFin entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}