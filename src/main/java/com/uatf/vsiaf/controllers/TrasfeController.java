package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Trasfe;
import com.uatf.vsiaf.services.TrasfeService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/trasfe")
@Tag(name = "Trasfe", description = "Transferencias de activos (VSIAF)")
public class TrasfeController extends AuditTransactionController<Trasfe, Long> {

    @Autowired
    private TrasfeService service;

    @Override
    protected BaseCrudService<Trasfe, Long> getService() {
        return service;
    }
}
