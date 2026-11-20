package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Config;
import com.uatf.vsiaf.services.ConfigService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/config")
@Tag(name = "Config", description = "Configuraciones del sistema (VSIAF)")
public class ConfigController extends BaseCrudController<Config, Long> {

    @Autowired
    private ConfigService service;

    @Override
    protected BaseCrudService<Config, Long> getService() {
        return service;
    }
}
