package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Reval;
import com.uatf.vsiaf.services.RevalService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reval")
@Tag(name = "Reval", description = "Revalúos técnicos de activos (VSIAF)")
public class RevalController extends BaseCrudController<Reval, Long> {

    @Autowired
    private RevalService service;

    @Override
    protected BaseCrudService<Reval, Long> getService() {
        return service;
    }
}
