package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "estado_entidad")
@Schema(description = "Estructura institucional y gestión fiscal de la entidad (VSIAF)")
public class EstEnt {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(length = 4)
    @Size(max = 4, message = "La gestión no puede exceder 4 dígitos (año fiscal)")
    @Schema(description = "Gestión fiscal / año (ej: 2024)", example = "2024")
    private String gestion;

    @Schema(description = "Estado de actividad de la gestión", example = "true")
    private Boolean activo;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Entidad pública asociada")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
