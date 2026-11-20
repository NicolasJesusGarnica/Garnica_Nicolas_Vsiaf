package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auxiliares")
public class Auxiliar {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(name = "codigo_auxiliar", unique = true, length = 20)
    private String codigo;

    @Column(length = 200)
    private String nombre;

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
