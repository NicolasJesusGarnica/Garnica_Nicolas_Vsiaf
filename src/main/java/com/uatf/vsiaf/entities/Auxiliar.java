package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "auxiliares", uniqueConstraints = {
    @UniqueConstraint(name = "uq_codcont_auxiliar", columnNames = {"entidad", "unidad", "codcont_id", "codigo_auxiliar"})
})
public class Auxiliar {
    @Id
    @Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(length = 4)
    @Schema(description = "Código de entidad pública (ENTIDAD en auxiliar.DBF)", example = "143")
    private String entidad = "143";

    @Column(length = 5)
    @Schema(description = "Código de unidad administrativa (UNIDAD en auxiliar.DBF)", example = "UATF")
    private String unidad = "UATF";

    @Column(name = "codigo_auxiliar", length = 20, nullable = false)
    @Schema(description = "Código del auxiliar dentro del grupo contable (CODAUX en auxiliar.DBF)", example = "1")
    private String codigo;

    @Column(length = 200, nullable = false)
    @Schema(description = "Nombre del auxiliar (NOMAUX en auxiliar.DBF)", example = "CASA DE 50 M")
    private String nombre;

    @Column(length = 500)
    @Schema(description = "Observaciones del auxiliar (OBSERV en auxiliar.DBF)", nullable = true)
    private String observaciones;

    @Column(name = "feult")
    @Schema(description = "Fecha de última actualización (FEULT en auxiliar.DBF)", example = "2026-11-27")
    private LocalDate feult = LocalDate.now();

    @Column(name = "usuar", length = 20)
    @Schema(description = "Usuario responsable de la última operación (USUAR en auxiliar.DBF)", example = "admin")
    private String usuar = "admin";

    @ManyToOne
    @JoinColumn(name = "objgasto_id")
    private Objgasto objgasto;

    @ManyToOne
    @JoinColumn(name = "codcont_id")
    private Codcont codcont;

    @JsonProperty("objgastoId")
    public void setObjgastoId(Long id) {
        if (id != null) {
            Objgasto o = new Objgasto();
            o.setId(id);
            this.objgasto = o;
        }
    }

    @JsonProperty("codcontId")
    public void setCodcontId(Long id) {
        if (id != null) {
            Codcont c = new Codcont();
            c.setId(id);
            this.codcont = c;
        }
    }
}
