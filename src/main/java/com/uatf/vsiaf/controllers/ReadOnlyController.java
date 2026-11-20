package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Controlador base para Catálogos Maestros Fijos del Estado (VSIAF).
 * Por regla de negocio y normativa, estos catálogos son predefinidos y de SOLO LECTURA.
 * No se permite creación (POST), modificación (PUT/PATCH) ni eliminación (DELETE).
 */
public abstract class ReadOnlyController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping
    @Operation(summary = "Listar catálogo oficial (Solo Lectura)", 
               description = "Retorna el listado completo del catálogo oficial predefinido por normativa nacional.")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Consultar registro por ID", 
               description = "Busca y retorna un elemento del catálogo por su identificador único. Retorna 404 si no existe.")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }
}
