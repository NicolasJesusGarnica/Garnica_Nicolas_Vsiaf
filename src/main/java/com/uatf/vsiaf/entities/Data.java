package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "data_sistema")
@Schema(description = "Variables y datos de configuración global del sistema VSIAF")
public class Data {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(length = 50)
    @Schema(description = "Clave de la variable del sistema", example = "VERSION")
    private String claveVariable;

    @Column(length = 255)
    @Schema(description = "Valor de la variable del sistema", example = "2.0")
    private String valorVariable;

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
