package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controlador base para Clasificadores Normativos Oficiales (VSIAF).
 * Permite consulta y registro de nuevos clasificadores oficiales aprobados por resolución.
 * NO permite eliminación (DELETE) para resguardar la vigencia y coherencia contable.
 */
public abstract class CatalogController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping
    @Operation(summary = "Listar clasificador", 
               description = "Retorna la lista completa de elementos registrados en el clasificador normativo.")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener elemento por ID", 
               description = "Busca y retorna un registro del clasificador por su identificador.")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    @Operation(summary = "Incorporar nuevo clasificador oficial", 
               description = "Registra un nuevo elemento clasificado en el catálogo oficial. Valida duplicados de código o ID.")
    public ResponseEntity<T> guardar(@RequestBody T entity) {
        T creado = getService().create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
}
