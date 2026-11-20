package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Baja;
import com.uatf.vsiaf.services.BajaService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/baja")
@Tag(name = "Baja", description = "Cabecera de bajas de activos (VSIAF)")
public class BajaController extends BaseCrudController<Baja, Long> {

    @Autowired
    private BajaService service;

    @Override
    protected BaseCrudService<Baja, Long> getService() {
        return service;
    }
}
