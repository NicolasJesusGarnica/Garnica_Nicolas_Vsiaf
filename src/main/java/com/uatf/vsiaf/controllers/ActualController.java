package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Actual;
import com.uatf.vsiaf.services.ActualService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/actual")
@Tag(name = "Activos Actuales (Inmuebles)", description = "Controlador para gestión de activos fijos e inmuebles (VSIAF)")
public class ActualController extends BaseCrudController<Actual, Long> {

    @Autowired
    private ActualService service;

    @Override
    protected BaseCrudService<Actual, Long> getService() {
        return service;
    }

    @GetMapping("/buscar")
    @Operation(summary = "Buscar inmueble por código de activo", description = "Recupera un inmueble a partir de su código único asignado.")
    public ResponseEntity<Actual> buscarPorCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(service.findByCodigoActivo(codigo));
    }
}
