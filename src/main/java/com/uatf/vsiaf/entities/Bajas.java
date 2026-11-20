package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bajas_detalle")
public class Bajas {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental del detalle de baja")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(length = 255)
    private String observacion;

    private Double montoHistorial;

    @ManyToOne
    @JoinColumn(name = "baja_id")
    private Baja baja;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;

    @JsonProperty("bajaId")
    public void setBajaId(Long id) {
        if (id != null) {
            Baja b = new Baja();
            b.setId(id);
            this.baja = b;
        }
    }

    @JsonProperty("activoId")
    public void setActivoId(Long id) {
        if (id != null) {
            Actual a = new Actual();
            a.setId(id);
            this.activo = a;
        }
    }
}
