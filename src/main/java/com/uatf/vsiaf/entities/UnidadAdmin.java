package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "unidad_admin")
public class UnidadAdmin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_unidad", unique = true, length = 20)
    private String codigoUnidad;

    @Column(length = 150)
    private String nombre;

    @Column(length = 100)
    private String ciudad;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;
    public void setId(Long id) { this.id = id; }
}

