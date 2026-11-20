package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.CtaPar;
import com.uatf.vsiaf.services.CtaParService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cta-par")
@Tag(name = "CtaPar", description = "Cuentas y partidas presupuestarias (VSIAF)")
public class CtaParController extends BaseCrudController<CtaPar, Long> {

    @Autowired
    private CtaParService service;

    @Override
    protected BaseCrudService<CtaPar, Long> getService() {
        return service;
    }
}
