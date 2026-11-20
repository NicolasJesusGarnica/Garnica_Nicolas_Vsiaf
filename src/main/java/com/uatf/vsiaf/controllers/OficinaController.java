package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Oficina;
import com.uatf.vsiaf.services.OficinaService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/oficinas")
@Tag(name = "Oficina", description = "Oficinas institucionales (VSIAF)")
public class OficinaController extends BaseCrudController<Oficina, Long> {

    @Autowired
    private OficinaService service;

    @Override
    protected BaseCrudService<Oficina, Long> getService() {
        return service;
    }
}
