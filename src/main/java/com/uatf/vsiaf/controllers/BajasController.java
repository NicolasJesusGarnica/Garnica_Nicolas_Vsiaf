package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Bajas;
import com.uatf.vsiaf.services.BajasService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/bajas-detalle", "/api/bajas"})
@Tag(name = "Bajas", description = "Detalle de bajas de activos (VSIAF)")
public class BajasController extends BaseCrudController<Bajas, Long> {

    @Autowired
    private BajasService service;

    @Override
    protected BaseCrudService<Bajas, Long> getService() {
        return service;
    }
}
