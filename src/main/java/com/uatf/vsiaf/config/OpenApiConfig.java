package com.uatf.vsiaf.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST Sistema de Activos Fijos - VSIAF 2.0")
                        .version("2.0.0")
                        .description("Backend RESTful para la gestión y control de activos fijos, entidades, unidades, oficinas y responsables (UATF - Tecnologías Emergentes SIS-414). Implementa operaciones CRUD completas con los 6 métodos HTTP y validación de duplicados.")
                        .contact(new Contact().name("Nicolás Garnica").email("njgarnicaz@gmail.com")));
    }
}
