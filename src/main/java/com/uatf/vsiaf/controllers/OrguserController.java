package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Orguser;
import com.uatf.vsiaf.services.OrguserService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orguser")
@Tag(name = "Orguser", description = "Usuarios por entidad (VSIAF)")
public class OrguserController extends ConfigurableEntityController<Orguser, Long> {

    @Autowired
    private OrguserService service;

    @Override
    protected BaseCrudService<Orguser, Long> getService() {
        return service;
    }
}
