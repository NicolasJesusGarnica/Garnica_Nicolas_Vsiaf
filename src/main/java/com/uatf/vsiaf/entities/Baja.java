package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "bajas")
public class Baja {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaBaja;

    private String motivo;
    private String resolucion;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;
}