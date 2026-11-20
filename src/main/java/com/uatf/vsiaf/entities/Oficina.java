package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oficinas")
public class Oficina {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador autoincremental de la oficina")
    @com.fasterxml.jackson.annotation.JsonProperty(access = com.fasterxml.jackson.annotation.JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "codigo_oficina", unique = true, length = 20)
    private String codigoOficina;

    @Column(length = 150)
    private String nombre;

    @Column(length = 255)
    private String observacion;

    @Column(length = 20)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "unidad_admin_id")
    private UnidadAdmin unidadAdmin;

    @JsonProperty("unidadAdminId")
    public void setUnidadAdminId(Long id) {
        if (id != null) {
            UnidadAdmin u = new UnidadAdmin();
            u.setId(id);
            this.unidadAdmin = u;
        }
    }
}
