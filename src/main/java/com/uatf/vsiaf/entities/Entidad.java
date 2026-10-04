package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "entidades")
public class Entidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_entidad", unique = true, length = 10)
    private String codigoEntidad;

    @Column(length = 200)
    private String descripcion;

    @Column(length = 50)
    private String sigla;
}