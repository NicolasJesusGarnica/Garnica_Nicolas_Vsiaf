package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "usuarios_passw")
public class Passw {
    @Id
    private Long id;

    @Column(unique = true, length = 50)
    private String username;

    @Column(length = 255)
    private String password;

    private Boolean activo;

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
