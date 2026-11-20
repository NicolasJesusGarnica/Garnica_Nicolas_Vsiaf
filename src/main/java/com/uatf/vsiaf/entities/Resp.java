package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "responsables")
public class Resp {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental del responsable")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "carnet_identidad", unique = true, length = 15)
    private String ci;

    @Column(length = 20)
    private String expedido;

    @Column(length = 100)
    private String nombres;

    @Column(length = 100)
    private String apellidos;

    @Column(length = 100)
    private String cargo;

    @Column(length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

    @JsonProperty("oficinaId")
    public void setOficinaId(Long id) {
        if (id != null) {
            Oficina o = new Oficina();
            o.setId(id);
            this.oficina = o;
        }
    }
}
