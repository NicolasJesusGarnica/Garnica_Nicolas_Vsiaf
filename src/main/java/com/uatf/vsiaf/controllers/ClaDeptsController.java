package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.ClaDepts;
import com.uatf.vsiaf.services.ClaDeptsService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cla-depts")
@Tag(name = "ClaDepts", description = "Clasificador de departamentos (VSIAF)")
public class ClaDeptsController extends ReadOnlyController<ClaDepts, Long> {

    @Autowired
    private ClaDeptsService service;

    @Override
    protected BaseCrudService<ClaDepts, Long> getService() {
        return service;
    }
}
