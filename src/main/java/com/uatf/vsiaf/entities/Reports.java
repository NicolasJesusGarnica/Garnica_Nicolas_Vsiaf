package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reportes")
@Schema(description = "Catálogo e historial de reportes oficiales emitidos (VSIAF)")
public class Reports {

    @Id
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental del reporte")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(length = 150)
    @Schema(description = "Nombre o título del reporte oficial", example = "Reporte de Oficinas con sus Responsables")
    private String nombreReporte;

    @Column(length = 255)
    @Schema(description = "Ruta o formato del archivo de reporte", example = "rep7.frx")
    private String rutaArchivo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Usuario que generó o tiene acceso al reporte")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Passw usuario;

    @JsonProperty("usuarioId")
    public void setUsuarioId(Long id) {
        if (id != null) {
            Passw p = new Passw();
            p.setId(id);
            this.usuario = p;
        }
    }
}
