package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "codigos_contables")
public class Codcont {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental de la cuenta contable")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "cuenta_contable", unique = true, length = 20)
    private String cuentaContable;

    @Column(length = 200)
    private String descripcion;

    @Column(name = "vida_util_anios")
    private Integer vidaUtilAnios;

    @Column(name = "coeficiente_depreciacion")
    private Double coeficienteDepreciacion;
}