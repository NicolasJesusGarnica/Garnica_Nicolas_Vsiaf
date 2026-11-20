package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "estados_bien")
@Schema(description = "Estados de conservación del bien: Bueno, Regular, Malo (VSIAF)")
public class Estado {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Código de estado autoincremental (1..3)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, length = 5)
    @Size(max = 5)
    @Schema(description = "Sigla del estado (ej: B, R, M)", example = "B")
    private String sigla;

    @Column(length = 10)
    @Size(max = 10, message = "La descripción no puede exceder 10 caracteres (NOMESTADO C10 en VSIAF)")
    @Schema(description = "Nombre oficial del estado ('Bueno', 'Regular', 'Malo')", example = "Bueno")
    private String descripcion;
}
