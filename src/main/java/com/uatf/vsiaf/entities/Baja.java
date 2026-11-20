package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "bajas")
public class Baja {
    @Id
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaBaja;

    private String motivo;
    private String resolucion;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;

    @JsonProperty("activoId")
    public void setActivoId(Long id) {
        if (id != null) {
            Actual a = new Actual();
            a.setId(id);
            this.activo = a;
        }
    }
}
