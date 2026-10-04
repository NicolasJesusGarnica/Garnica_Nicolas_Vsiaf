package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "revaluos")
public class Reval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaRevaluo;

    private Double valorNuevo;
    private Double vidaUtilNueva;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;
}