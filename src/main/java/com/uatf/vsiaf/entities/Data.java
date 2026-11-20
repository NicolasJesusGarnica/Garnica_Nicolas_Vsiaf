package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "data_sistema")
public class Data {
    @Id
    private Long id;

    @Column(length = 50)
    private String claveVariable;

    @Column(length = 255)
    private String valorVariable;

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
