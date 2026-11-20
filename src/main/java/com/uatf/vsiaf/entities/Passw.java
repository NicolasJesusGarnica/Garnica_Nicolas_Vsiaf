package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "usuarios_passw")
public class Passw {
    @Id
    private Long id;

    @Column(unique = true, length = 10)
    @Size(max = 10, message = "El username no puede exceder 10 caracteres (C10 en VSIAF)")
    private String username;

    @Column(length = 255)
    private String password;

    @Column(name = "nombre_completo", length = 40)
    @Size(max = 40, message = "El nombre completo no puede exceder 40 caracteres (C40 en VSIAF)")
    private String nombreCompleto;

    @Column(length = 50)
    @Size(max = 50, message = "La descripción no puede exceder 50 caracteres (C50 en VSIAF)")
    private String descripcion;

    @Column(length = 8)
    @Size(max = 8, message = "El grupo no puede exceder 8 caracteres ('Admin', 'User', 'VSIAF')")
    private String grupo;

    private Boolean activo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ultimo_acceso")
    private Date fechaUltimoAcceso;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Resp responsable;

    @JsonProperty("responsableId")
    public void setResponsableId(Long id) {
        if (id != null) {
            Resp r = new Resp();
            r.setId(id);
            this.responsable = r;
        }
    }
}
