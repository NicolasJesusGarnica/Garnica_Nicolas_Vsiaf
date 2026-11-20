package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador base para entidades de configuración institucional y parámetros de usuario (VSIAF).
 * Permite consulta (GET) y actualización (PUT). No permite eliminación (DELETE).
 */
public abstract class ConfigurableEntityController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping
    @Operation(summary = "Listar configuraciones", description = "Retorna la lista de configuraciones y parámetros activos.")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener configuración por ID", description = "Consulta una configuración específica por su identificador.")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar configuración", description = "Modifica los parámetros de configuración institucional.")
    public ResponseEntity<T> actualizar(@PathVariable ID id, @RequestBody T entity) {
        T actualizado = getService().update(id, entity);
        return ResponseEntity.ok(actualizado);
    }
}
