package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Objgasto;
import com.uatf.vsiaf.services.ObjgastoService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/objgasto")
@Tag(name = "Objgasto", description = "Objetos de gasto (VSIAF)")
public class ObjgastoController extends BaseCrudController<Objgasto, Long> {

    @Autowired
    private ObjgastoService service;

    @Override
    protected BaseCrudService<Objgasto, Long> getService() {
        return service;
    }
}
