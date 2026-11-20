package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas_partidas")
@Schema(description = "Relación de cuentas contables con partidas presupuestarias (VSIAF)")
public class CtaPar {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codcont_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Cuenta contable asociada")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Codcont codcont;

    @ManyToOne
    @JoinColumn(name = "objgasto_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Partida presupuestaria asociada")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Objgasto objgasto;

    @JsonProperty("codcontId")
    @Schema(description = "ID de la cuenta contable (FK a Codcont)", example = "1")
    public void setCodcontId(Long id) {
        if (id != null) {
            Codcont c = new Codcont();
            c.setId(id);
            this.codcont = c;
        }
    }

    @JsonProperty("objgastoId")
    @Schema(description = "ID del objeto de gasto / partida (FK a Objgasto)", example = "1")
    public void setObjgastoId(Long id) {
        if (id != null) {
            Objgasto o = new Objgasto();
            o.setId(id);
            this.objgasto = o;
        }
    }
}
