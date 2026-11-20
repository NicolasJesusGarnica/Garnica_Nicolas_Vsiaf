package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "entidades")
public class Entidad {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(name = "codigo_entidad", unique = true, length = 10)
    private String codigoEntidad;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 50)
    private String sigla;
}
