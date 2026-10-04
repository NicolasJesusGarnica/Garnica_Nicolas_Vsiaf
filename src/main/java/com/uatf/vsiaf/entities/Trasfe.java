package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "transferencias")
public class Trasfe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Date fechaTrasfe;

    private String motivo;

    @ManyToOne
    @JoinColumn(name = "activo_id")
    private Actual activo;

    @ManyToOne
    @JoinColumn(name = "resp_origen_id")
    private Resp respOrigen;

    @ManyToOne
    @JoinColumn(name = "resp_destino_id")
    private Resp respDestino;
}