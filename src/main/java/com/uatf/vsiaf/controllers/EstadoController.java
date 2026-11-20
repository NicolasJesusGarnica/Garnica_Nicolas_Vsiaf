package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Estado;
import com.uatf.vsiaf.services.EstadoService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/estados")
@Tag(name = "Estado", description = "Estados de conservación del bien (VSIAF)")
public class EstadoController extends BaseCrudController<Estado, Long> {

    @Autowired
    private EstadoService service;

    @Override
    protected BaseCrudService<Estado, Long> getService() {
        return service;
    }
}
