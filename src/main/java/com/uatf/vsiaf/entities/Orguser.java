package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "org_usuarios")
public class Orguser {
    @Id
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passw_id")
    private Passw usuario;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;

    @JsonProperty("usuarioId")
    public void setUsuarioId(Long id) {
        if (id != null) {
            Passw p = new Passw();
            p.setId(id);
            this.usuario = p;
        }
    }

    @JsonProperty("entidadId")
    public void setEntidadId(Long id) {
        if (id != null) {
            Entidad e = new Entidad();
            e.setId(id);
            this.entidad = e;
        }
    }
}
