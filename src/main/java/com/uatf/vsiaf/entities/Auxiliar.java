package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "auxiliares")
public class Auxiliar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_auxiliar", unique = true, length = 20)
    private String codigo;

    @Column(length = 200)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "objgasto_id")
    private Objgasto objgasto;
}