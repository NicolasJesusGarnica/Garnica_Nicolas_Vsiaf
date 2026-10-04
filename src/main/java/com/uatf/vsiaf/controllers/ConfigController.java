package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Config;
import com.uatf.vsiaf.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/config")
public class ConfigController {
    @Autowired private ConfigService service;
    @GetMapping public List<Config> listar() { return service.findAll(); }
    @PostMapping public Config guardar(@RequestBody Config entity) { return service.save(entity); }
    @GetMapping("/{id}") public Config obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Config actualizar(@PathVariable Long id, @RequestBody Config entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}