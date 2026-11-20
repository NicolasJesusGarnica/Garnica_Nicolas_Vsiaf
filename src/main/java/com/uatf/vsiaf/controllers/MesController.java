package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Mes;
import com.uatf.vsiaf.services.MesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/mes")
@Tag(name = "Mes", description = "Períodos contables mensuales y cierres de gestión (VSIAF)")
public class MesController {

    @Autowired
    private MesService service;

    @GetMapping
    @Operation(summary = "Listar períodos mensuales", 
               description = "Retorna la lista de meses aperturados en la gestión contable fiscal.")
    public ResponseEntity<List<Mes>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener mes por ID", 
               description = "Consulta el detalle y estado de cierre de un mes por su identificador.")
    public ResponseEntity<Mes> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Aperturar siguiente mes (Autoincremental)", 
               description = "Apertura automáticamente el siguiente mes correlativo (1 a 12) asignándole su nombre oficial. No requiere definir qué mes es. Si se intenta superar los 12 meses, responde 409/400 con regla de negocio.")
    public ResponseEntity<Mes> aperturarMes(@RequestBody(required = false) Mes mesOptional) {
        Mes creado = service.aperturarSiguienteMes(mesOptional);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Cierre / Apertura contable de mes", 
               description = "Modifica el estado de cierre contable mensual ('cerrado: true/false') para cálculo de depreciaciones y UFVs.")
    public ResponseEntity<Mes> cambiarEstadoCierre(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Mes actualizado = service.patch(id, campos);
        return ResponseEntity.ok(actualizado);
    }
}
