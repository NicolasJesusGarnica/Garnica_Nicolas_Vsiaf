package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "codigos_contables")
public class Codcont {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
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
