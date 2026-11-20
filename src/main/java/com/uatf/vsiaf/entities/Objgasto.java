package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "objeto_gasto")
public class Objgasto {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @Column(unique = true, length = 10)
    private String partida;

    @Column(length = 200)
    private String descripcion;
}
