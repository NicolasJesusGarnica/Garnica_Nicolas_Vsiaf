package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "backups_log")
public class Backups {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental del log de respaldo")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_backup")
    private Date fechaBackup;

    @Column(name = "nombre_archivo", length = 200)
    private String nombreArchivo;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
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
