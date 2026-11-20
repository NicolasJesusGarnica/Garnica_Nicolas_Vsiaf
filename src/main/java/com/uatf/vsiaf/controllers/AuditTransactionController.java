package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador base para Movimientos Transaccionales y Bitácoras de Auditoría (VSIAF).
 * Por normas contables y de auditoría gubernamental, las actas y movimientos emitidos
 * (transferencias, revalúos, bajas, respaldos) son INMUTABLES: no se permite modificarlos (PUT/PATCH)
 * ni eliminarlos (DELETE).
 */
public abstract class AuditTransactionController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping
    @Operation(summary = "Listar historial de movimientos", 
               description = "Retorna el historial completo de actas y transacciones contables registradas.")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener acta / movimiento por ID", 
               description = "Consulta el detalle de un acta o transacción por su ID único.")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    @Operation(summary = "Emitir nueva transacción / acta", 
               description = "Registra una nueva transacción inmutable en el sistema contable gubernamental.")
    public ResponseEntity<T> registrar(@RequestBody T entity) {
        T creado = getService().create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
