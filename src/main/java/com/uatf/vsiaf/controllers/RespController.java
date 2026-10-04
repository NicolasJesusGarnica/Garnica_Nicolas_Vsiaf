package com.uatf.vsiaf.controllers;
import com.uatf.vsiaf.entities.Resp;
import com.uatf.vsiaf.services.RespService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/resp")
public class RespController {
    @Autowired private RespService service;
    @GetMapping public List<Resp> listar() { return service.findAll(); }
    @PostMapping public Resp guardar(@RequestBody Resp entity) { return service.save(entity); }
    @GetMapping("/{id}") public Resp obtenerPorId(@PathVariable Long id) { return service.findById(id); }
    @PutMapping("/{id}") public Resp actualizar(@PathVariable Long id, @RequestBody Resp entity) { entity.setId(id); return service.save(entity); }
    @DeleteMapping("/{id}") public void eliminar(@PathVariable Long id) { service.deleteById(id); }
}