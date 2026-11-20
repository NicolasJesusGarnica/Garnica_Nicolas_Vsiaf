package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "estado_entidad")
public class EstEnt {
    @Id
    private Long id;

    @Column(length = 10)
    private String gestion;

    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

    @JsonProperty("entidadId")
    public void setEntidadId(Long id) {
        if (id != null) {
            Entidad e = new Entidad();
            e.setId(id);
            this.entidad = e;
        }
    }
}
