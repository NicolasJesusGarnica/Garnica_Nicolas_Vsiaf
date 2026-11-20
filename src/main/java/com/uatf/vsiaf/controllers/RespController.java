package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.entities.Resp;
import com.uatf.vsiaf.services.RespService;
import com.uatf.vsiaf.services.BaseCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resp")
@Tag(name = "Resp", description = "Responsables y custodios de activos (VSIAF)")
public class RespController extends BaseCrudController<Resp, Long> {

    @Autowired
    private RespService service;

    @Override
    protected BaseCrudService<Resp, Long> getService() {
        return service;
    }
}
