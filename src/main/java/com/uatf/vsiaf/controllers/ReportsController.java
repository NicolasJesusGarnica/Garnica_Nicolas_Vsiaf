package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Reports;
import com.uatf.vsiaf.services.ReportsService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reports")
@Tag(name = "Reports", description = "Historial de reportes emitidos (VSIAF)")
public class ReportsController extends BaseCrudController<Reports, Long> {

    @Autowired
    private ReportsService service;

    @Override
    protected BaseCrudService<Reports, Long> getService() {
        return service;
    }
}
