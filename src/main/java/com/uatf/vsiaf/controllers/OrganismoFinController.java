package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.OrganismoFin;
import com.uatf.vsiaf.services.OrganismoFinService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/organismo-fin")
@Tag(name = "OrganismoFin", description = "Organismos financiadores (VSIAF)")
public class OrganismoFinController extends BaseCrudController<OrganismoFin, Long> {

    @Autowired
    private OrganismoFinService service;

    @Override
    protected BaseCrudService<OrganismoFin, Long> getService() {
        return service;
    }
}
