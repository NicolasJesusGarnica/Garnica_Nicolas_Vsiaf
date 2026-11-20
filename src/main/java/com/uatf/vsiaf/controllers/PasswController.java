package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Passw;
import com.uatf.vsiaf.services.PasswService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/passw")
@Tag(name = "Password / Usuarios (Passw)", description = "Gestión de credenciales, roles y accesos de usuario (VSIAF)")
public class PasswController {

    @Autowired
    private PasswService service;

    @GetMapping
    @Operation(summary = "Listar usuarios del sistema", 
               description = "Retorna la lista de usuarios registrados con sus roles y estados de habilitación.")
    public ResponseEntity<List<Passw>> listar() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener usuario por ID", 
               description = "Busca y retorna el perfil de un usuario por su ID único.")
    public ResponseEntity<Passw> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo usuario", 
               description = "Crea un nuevo usuario en el sistema. Valida que el nombre de usuario (username max 10 chars) no esté duplicado, asigna rol y estado activo por defecto.")
    public ResponseEntity<Passw> registrar(@Valid @RequestBody Passw passw) {
        Passw creado = service.create(passw);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar datos de usuario", 
               description = "Reemplaza los datos del usuario (nombre completo, descripción, rol). Retorna 404 si el ID no existe.")
    public ResponseEntity<Passw> actualizar(@PathVariable Long id, @Valid @RequestBody Passw passw) {
        Passw actualizado = service.update(id, passw);
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Actualización parcial (Cambio de contraseña o Desactivación)", 
               description = "Permite cambiar la contraseña o inhabilitar al usuario enviando únicamente {'activo': false} o {'password': 'nueva'}.")
    public ResponseEntity<Passw> actualizarParcial(@PathVariable Long id, @RequestBody Map<String, Object> campos) {
        Passw actualizado = service.patch(id, campos);
        return ResponseEntity.ok(actualizado);
    }
}
