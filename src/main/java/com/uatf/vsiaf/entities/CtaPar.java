package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas_partidas")
public class CtaPar {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codcont_id")
    private Codcont codcont;

    @ManyToOne
    @JoinColumn(name = "objgasto_id")
    private Objgasto objgasto;

    @JsonProperty("codcontId")
    public void setCodcontId(Long id) {
        if (id != null) {
            Codcont c = new Codcont();
            c.setId(id);
            this.codcont = c;
        }
    }

    @JsonProperty("objgastoId")
    public void setObjgastoId(Long id) {
        if (id != null) {
            Objgasto o = new Objgasto();
            o.setId(id);
            this.objgasto = o;
        }
    }
}
