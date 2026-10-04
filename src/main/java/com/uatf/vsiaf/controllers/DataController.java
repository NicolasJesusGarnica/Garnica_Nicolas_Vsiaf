package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Data;
import com.uatf.vsiaf.services.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {
    @Autowired private DataService service;
    @GetMapping public List<Data> listar() { return service.findAll(); }
    @PostMapping public Data guardar(@RequestBody Data entity) { return service.save(entity); }
    @GetMapping("/{id}") public Data obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Data actualizar(@PathVariable Long id, @RequestBody Data entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}