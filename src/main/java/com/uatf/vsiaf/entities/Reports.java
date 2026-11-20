package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "reportes")
public class Reports {
    @Id
    private Long id;

    @Column(length = 150)
    private String nombreReporte;

    @Column(length = 255)
    private String rutaArchivo;

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
