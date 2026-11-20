package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.UnidadAdmin;
import com.uatf.vsiaf.services.UnidadAdminService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unidadadmin")
@Tag(name = "UnidadAdmin", description = "Unidades administrativas (VSIAF)")
public class UnidadAdminController extends BaseCrudController<UnidadAdmin, Long> {

    @Autowired
    private UnidadAdminService service;

    @Override
    protected BaseCrudService<UnidadAdmin, Long> getService() {
        return service;
    }
}
