package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "meses")
public class Mes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_mes")
    private Integer numeroMes;

    @Column(name = "nombre_mes", length = 20)
    private String nombreMes;

    private Boolean cerrado;
}