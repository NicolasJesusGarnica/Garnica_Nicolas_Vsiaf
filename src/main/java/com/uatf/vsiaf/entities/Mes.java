package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meses")
public class Mes {
    @Id
    private Long id;

    @Column(name = "numero_mes")
    private Integer numeroMes;

    @Column(name = "nombre_mes", length = 20)
    private String nombreMes;

    private Boolean cerrado;

    @ManyToOne
    @JoinColumn(name = "est_ent_id")
    private EstEnt estadoEntidad;

    @JsonProperty("estadoEntidadId")
    public void setEstadoEntidadId(Long id) {
        if (id != null) {
            EstEnt e = new EstEnt();
            e.setId(id);
            this.estadoEntidad = e;
        }
    }
}
