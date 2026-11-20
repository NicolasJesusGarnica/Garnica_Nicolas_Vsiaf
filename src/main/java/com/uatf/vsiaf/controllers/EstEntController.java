package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.EstEnt;
import com.uatf.vsiaf.services.EstEntService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/est-ent")
@Tag(name = "EstEnt", description = "Estado de entidad por gestión (VSIAF)")
public class EstEntController extends BaseCrudController<EstEnt, Long> {

    @Autowired
    private EstEntService service;

    @Override
    protected BaseCrudService<EstEnt, Long> getService() {
        return service;
    }
}
