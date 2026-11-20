package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Backups;
import com.uatf.vsiaf.services.BackupsService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/backups")
@Tag(name = "Backups", description = "Logs y copias de seguridad (VSIAF)")
public class BackupsController extends AuditTransactionController<Backups, Long> {

    @Autowired
    private BackupsService service;

    @Override
    protected BaseCrudService<Backups, Long> getService() {
        return service;
    }
}
