package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.services.CodcontService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/codcont")
@Tag(name = "Codcont", description = "Grupos Contables (VSIAF) - Catálogo oficial D.S. 24051 y configuración de depreciación/actualización")
public class CodcontController {

    @Autowired
    private CodcontService service;

    @GetMapping
    @Operation(summary = "Listar grupos contables", description = "Retorna los rubros contables oficiales del catálogo D.S. 24051 precargados en el sistema.")
    public ResponseEntity<List<Codcont>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener grupo contable por ID", description = "Consulta un grupo contable por su identificador único (ej. 1 para Edificaciones, 15 para Computación).")
    public ResponseEntity<Codcont> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Conmutar depreciación y actualización (PATCH)", description = "Modifica únicamente si el rubro se deprecia (deprecia: true/false), si se actualiza por UFV (actualiza: true/false) y notas de observaciones, tal como lo hace el VSIAF al pulsar 'Modificar'.")
    public ResponseEntity<Codcont> actualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        return ResponseEntity.ok(service.patch(id, campos));
    }
}
