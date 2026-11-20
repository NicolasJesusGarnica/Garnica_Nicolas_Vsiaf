package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "configuraciones")
@Schema(description = "Configuraciones institucionales del sistema VSIAF")
public class Config {

    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(name = "clave_config", unique = true, length = 50)
    @Schema(description = "Clave del parámetro o configuración", example = "ENTIDAD")
    private String clave;

    @Column(length = 255)
    @Schema(description = "Valor asignado al parámetro", example = "Universidad Autónoma Tomás Frías")
    private String valor;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Entidad a la que aplica la configuración")
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
