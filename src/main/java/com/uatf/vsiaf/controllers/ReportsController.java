package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Reports;
import com.uatf.vsiaf.services.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportsController {
    @Autowired private ReportsService service;
    @GetMapping public List<Reports> listar() { return service.findAll(); }
    @PostMapping public Reports guardar(@RequestBody Reports entity) { return service.save(entity); }
    @GetMapping("/{id}") public Reports obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Reports actualizar(@PathVariable Long id, @RequestBody Reports entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}