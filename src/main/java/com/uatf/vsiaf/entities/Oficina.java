package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "oficinas")
public class Oficina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_oficina", unique = true, length = 20)
    private String codigoOficina;

    @Column(length = 150)
    private String nombre;

    @ManyToOne
    @JoinColumn(name = "unidad_admin_id")
    private UnidadAdmin unidadAdmin;
}