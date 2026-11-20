package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organismos_fin")
public class OrganismoFin {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental del organismo financiador")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "codigo_of", unique = true, length = 10)
    private String codigoOf;

    @Column(length = 150)
    private String sigla;

    @Column(length = 255)
    private String descripcion;
}