package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Entidad;
import com.uatf.vsiaf.services.EntidadService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entidades")
@Tag(name = "Entidad", description = "Entidades públicas institucionales (VSIAF)")
public class EntidadController extends BaseCrudController<Entidad, Long> {

    @Autowired
    private EntidadService service;

    @Override
    protected BaseCrudService<Entidad, Long> getService() {
        return service;
    }
}
