package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "org_usuarios")
public class Orguser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "passw_id")
    private Passw usuario;

    @ManyToOne
    @JoinColumn(name = "entidad_id")
    private Entidad entidad;
}