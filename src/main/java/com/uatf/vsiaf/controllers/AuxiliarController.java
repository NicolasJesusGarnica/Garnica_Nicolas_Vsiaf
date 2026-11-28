package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Auxiliar;
import com.uatf.vsiaf.services.AuxiliarService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/api/auxiliares", "/api/auxiliar"})
@Tag(name = "Auxiliar", description = "Auxiliares contables (VSIAF)")
public class AuxiliarController extends BaseCrudController<Auxiliar, Long> {

    @Autowired
    private AuxiliarService service;

    @Override
    protected BaseCrudService<Auxiliar, Long> getService() {
        return service;
    }

    @GetMapping(params = "codcontId")
    @Operation(summary = "Listar auxiliares por grupo contable", description = "Retorna los auxiliares asociados a un grupo contable específico.")
    public ResponseEntity<List<Auxiliar>> listarPorCodcontParam(@RequestParam Long codcontId) {
        return ResponseEntity.ok(service.findByCodcontId(codcontId));
    }

    @GetMapping("/por-codcont/{codcontId}")
    @Operation(summary = "Listar auxiliares por grupo contable (ruta directa)", description = "Retorna los auxiliares asociados a un grupo contable específico.")
    public ResponseEntity<List<Auxiliar>> listarPorCodcontPath(@PathVariable Long codcontId) {
        return ResponseEntity.ok(service.findByCodcontId(codcontId));
    }

    @GetMapping("/{id}/dependencias")
    @Operation(summary = "Verificar si el auxiliar tiene activos fijos asociados", description = "Retorna true si existen registros en activos_actuales vinculados al auxiliar.")
    public ResponseEntity<java.util.Map<String, Boolean>> verificarDependencias(@PathVariable Long id) {
        return ResponseEntity.ok(java.util.Map.of("tieneDependencias", service.hasDependencies(id)));
    }
}
