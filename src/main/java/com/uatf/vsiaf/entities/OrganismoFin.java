package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "organismos_fin")
public class OrganismoFin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_of", unique = true, length = 10)
    private String codigoOf;

    @Column(length = 150)
    private String sigla;

    @Column(length = 255)
    private String descripcion;
}