package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Data;
import com.uatf.vsiaf.services.DataService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/data")
@Tag(name = "Data", description = "Variables y data del sistema (VSIAF)")
public class DataController extends BaseCrudController<Data, Long> {

    @Autowired
    private DataService service;

    @Override
    protected BaseCrudService<Data, Long> getService() {
        return service;
    }
}
