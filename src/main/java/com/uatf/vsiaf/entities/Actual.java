package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "activos_actuales")
public class Actual {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo_activo", unique = true, length = 50)
    private String codigoActivo;

    @Column(length = 255)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_incorporacion")
    private Date fechaIncorporacion;

    @Column(name = "costo_inicial")
    private Double costoInicial;

    @Column(name = "valor_neto")
    private Double valorNeto;

    @Column(length = 50)
    private String estadoBien;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    private Resp responsable;

    @ManyToOne
    @JoinColumn(name = "oficina_id")
    private Oficina oficina;

    @ManyToOne
    @JoinColumn(name = "codcont_id")
    private Codcont codcont;

    @ManyToOne
    @JoinColumn(name = "auxiliar_id")
    private Auxiliar auxiliar;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "organismo_fin_id")
    private OrganismoFin organismoFin;
}
