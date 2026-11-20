package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Auxiliar;
import com.uatf.vsiaf.services.AuxiliarService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auxiliares")
@Tag(name = "Auxiliar", description = "Auxiliares contables (VSIAF)")
public class AuxiliarController extends BaseCrudController<Auxiliar, Long> {

    @Autowired
    private AuxiliarService service;

    @Override
    protected BaseCrudService<Auxiliar, Long> getService() {
        return service;
    }
}
