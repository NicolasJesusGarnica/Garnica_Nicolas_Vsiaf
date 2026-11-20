package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "meses")
@Schema(description = "Entidad Mes: Período mensual contable fiscal (VSIAF)")
public class Mes {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental del mes (1..12)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "numero_mes")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Número de mes autoincremental (1..12)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Integer numeroMes;

    @Column(name = "nombre_mes", length = 10)
    @Size(max = 10, message = "El nombre del mes no puede exceder 10 caracteres (NOMMES C10 en VSIAF)")
    @Schema(description = "Nombre oficial del mes (ej: Enero, Febrero, Marzo)", example = "Enero")
    private String nombreMes;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado de cierre contable mensual (false al aperturar)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean cerrado;

    @ManyToOne
    @JoinColumn(name = "est_ent_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Gestión fiscal a la que pertenece el mes")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
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
