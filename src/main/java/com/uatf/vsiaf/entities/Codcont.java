package com.uatf.vsiaf.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "codigos_contables")
public class Codcont {
    @Id
    @Schema(description = "Identificador único del grupo (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(name = "cuenta_contable", unique = true, length = 20)
    @Schema(description = "Código de cuenta contable oficial (opcional, asignado automáticamente si se omite)", example = "12310")
    private String cuentaContable;

    @Column(length = 200)
    @Schema(description = "Nombre del rubro oficial (ej. EDIFICACIONES, MUEBLES Y ENSERES DE OFICINA, EQUIPOS DE COMPUTACION)", example = "EDIFICACIONES")
    private String descripcion;

    @Column(name = "vida_util_anios")
    @Schema(description = "Años de vida útil oficial (opcional, asignado automáticamente según el rubro bajo D.S. 24051)", example = "40")
    private Integer vidaUtilAnios;

    @Column(name = "coeficiente_depreciacion")
    @Schema(description = "Coeficiente anual de depreciación % (calculado automáticamente como 100/vida útil)", example = "2.5", accessMode = Schema.AccessMode.READ_ONLY)
    private Double coeficienteDepreciacion;

    @Column(name = "deprecia")
    @Schema(description = "Indica si el rubro se deprecia contablemente (casilla 'Deprecia' en VSIAF)", example = "true")
    private Boolean deprecia = true;

    @Column(name = "actualiza")
    @Schema(description = "Indica si el rubro se actualiza por UFV (casilla 'Actualiza' en VSIAF)", example = "true")
    private Boolean actualiza = true;

    @Column(name = "observaciones", length = 500)
    @Schema(description = "Notas u observaciones del grupo contable", example = "Sin observaciones")
    private String observaciones;
}
