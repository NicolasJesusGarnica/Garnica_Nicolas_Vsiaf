package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "cla_depts")
@Schema(description = "Clasificador de departamentos de Bolivia (VSIAF)")
public class ClaDepts {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental del departamento")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(unique = true, length = 10)
    @Size(max = 10, message = "El código no puede exceder 10 caracteres")
    @Schema(description = "Código del departamento (1..10)", example = "1")
    private String codigo;

    @Column(length = 15)
    @Size(max = 15, message = "La descripción no puede exceder 15 caracteres (DESC C15 en VSIAF)")
    @Schema(description = "Nombre del departamento", example = "POTOSI")
    private String nombre;

    @Column(length = 10)
    @Size(max = 10, message = "La sigla no puede exceder 10 caracteres")
    @Schema(description = "Sigla del departamento (ej: PTS, LPZ)", example = "PTS")
    private String sigla;
}
