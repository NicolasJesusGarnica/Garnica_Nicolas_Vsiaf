package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.dtos.ApiResponseMessage;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

public abstract class BaseCrudController<T, ID> {

    protected abstract BaseCrudService<T, ID> getService();

    @GetMapping
    @Operation(summary = "Listar todos los registros", description = "Retorna la lista completa de registros almacenados en la base de datos.")
    public ResponseEntity<List<T>> listar() {
        return ResponseEntity.ok(getService().findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener registro por ID", description = "Busca y retorna un registro por su ID único. Retorna 404 si no existe.")
    public ResponseEntity<T> obtenerPorId(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo registro", description = "Crea un nuevo registro. Si se envía un ID que ya existe, responde con 409 Conflict y mensaje de que ya ha sido registrado.")
    public ResponseEntity<T> guardar(@RequestBody T entity) {
        T creado = getService().create(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar registro completo por ID", description = "Reemplaza los datos del registro indicado por el ID. Retorna 404 si el ID no existe.")
    public ResponseEntity<T> actualizar(@PathVariable ID id, @RequestBody T entity) {
        T actualizado = getService().update(id, entity);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualizar parcialmente un registro por ID", description = "Modifica únicamente los campos enviados en el cuerpo JSON sin alterar los demás.")
    public ResponseEntity<T> actualizarParcial(@PathVariable ID id, @RequestBody Map<String, Object> campos) {
        T actualizado = getService().patch(id, campos);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar registro por ID", description = "Elimina el registro de la base de datos. Retorna 404 si no existe, o 409 si tiene dependencias de clave foránea.")
    public ResponseEntity<ApiResponseMessage<Void>> eliminar(@PathVariable ID id) {
        getService().deleteById(id);
        return ResponseEntity.ok(ApiResponseMessage.exito(HttpStatus.OK.value(), "Registro con ID " + id + " eliminado correctamente.", null));
    }
}
