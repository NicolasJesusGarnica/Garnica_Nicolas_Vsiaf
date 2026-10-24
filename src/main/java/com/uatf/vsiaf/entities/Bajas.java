package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "bajas_detalle")
public class Bajas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String observacion;

    private Double montoHistorial;

    @ManyToOne
    @JoinColumn(name = "baja_id")
    private Baja baja;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;
}
