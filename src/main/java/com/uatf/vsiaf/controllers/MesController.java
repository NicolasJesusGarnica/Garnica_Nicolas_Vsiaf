package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Mes;
import com.uatf.vsiaf.services.MesService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mes")
@Tag(name = "Mes", description = "Meses y cierres de gestión (VSIAF)")
public class MesController extends BaseCrudController<Mes, Long> {

    @Autowired
    private MesService service;

    @Override
    protected BaseCrudService<Mes, Long> getService() {
        return service;
    }
}
