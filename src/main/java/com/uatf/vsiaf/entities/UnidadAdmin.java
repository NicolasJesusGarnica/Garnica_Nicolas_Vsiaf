package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "unidad_admin")
public class UnidadAdmin {
    @Id
    private Long id;

    @Column(name = "codigo_unidad", unique = true, length = 20)
    private String codigoUnidad;

    @Column(length = 150)
    private String nombre;

    @Column(length = 100)
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

    @ManyToOne
    @JoinColumn(name = "cla_depts_id")
    private ClaDepts clasificadorDepto;

    @JsonProperty("entidadId")
    public void setEntidadId(Long id) {
        if (id != null) {
            Entidad e = new Entidad();
            e.setId(id);
            this.entidad = e;
        }
    }

    @JsonProperty("claDeptsId")
    public void setClaDeptsId(Long id) {
        if (id != null) {
            ClaDepts c = new ClaDepts();
            c.setId(id);
            this.clasificadorDepto = c;
        }
    }
}
