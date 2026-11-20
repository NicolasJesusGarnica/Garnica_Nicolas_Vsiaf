package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "configuraciones")
public class Config {
    @Id
    private Long id;

    @Column(name = "clave_config", unique = true, length = 50)
    private String clave;

    @Column(length = 255)
    private String valor;

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
