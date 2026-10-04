package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "cuentas_partidas")
public class CtaPar {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codcont_id")
    private Codcont codcont;

    @ManyToOne
    @JoinColumn(name = "objgasto_id")
    private Objgasto objgasto;
}