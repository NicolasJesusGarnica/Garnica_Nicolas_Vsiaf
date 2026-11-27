package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Codcont;
import com.uatf.vsiaf.services.CodcontService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/codcont")
@Tag(name = "Codcont", description = "Catálogo oficial de Grupos Contables D.S. 24051 (VSIAF - Solo Lectura)")
public class CodcontController extends ReadOnlyController<Codcont, Long> {

    @Autowired
    private CodcontService service;

    @Override
    protected BaseCrudService<Codcont, Long> getService() {
        return service;
    }
}
