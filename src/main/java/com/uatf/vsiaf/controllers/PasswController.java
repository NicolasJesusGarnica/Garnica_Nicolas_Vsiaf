package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Passw;
import com.uatf.vsiaf.services.PasswService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/passw")
@Tag(name = "Passw", description = "Credenciales y accesos de usuario (VSIAF)")
public class PasswController extends BaseCrudController<Passw, Long> {

    @Autowired
    private PasswService service;

    @Override
    protected BaseCrudService<Passw, Long> getService() {
        return service;
    }
}
