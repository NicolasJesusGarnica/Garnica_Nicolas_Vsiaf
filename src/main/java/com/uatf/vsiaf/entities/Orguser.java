package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "org_usuarios")
@Schema(description = "Asociación de usuarios con entidades institucionales (VSIAF)")
public class Orguser {

    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passw_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Cuenta de usuario asociada")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Passw usuario;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Entidad institucional asociada")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Entidad entidad;

    @JsonProperty("usuarioId")
    @Schema(description = "ID del usuario (FK a Passw)", example = "1")
    public void setUsuarioId(Long id) {
        if (id != null) {
            Passw p = new Passw();
            p.setId(id);
            this.usuario = p;
        }
    }

    @JsonProperty("entidadId")
    @Schema(description = "ID de la entidad institucional (FK a Entidad)", example = "1")
    public void setEntidadId(Long id) {
        if (id != null) {
            Entidad e = new Entidad();
            e.setId(id);
            this.entidad = e;
        }
    }
}
