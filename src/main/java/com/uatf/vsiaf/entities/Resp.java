package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "responsables")
public class Resp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "carnet_identidad", unique = true, length = 15)
    private String ci;

    @Column(length = 100)
    private String nombres;

    @Column(length = 100)
    private String apellidos;

    @Column(length = 100)
    private String cargo;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;
}