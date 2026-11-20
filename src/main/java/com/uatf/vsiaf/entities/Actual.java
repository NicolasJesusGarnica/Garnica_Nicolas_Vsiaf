package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "activos_actuales")
public class Actual {
    @Id
    @io.swagger.v3.oas.annotations.media.Schema(accessMode = io.swagger.v3.oas.annotations.media.Schema.AccessMode.READ_ONLY, description = "Identificador único autoincremental del activo")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @Column(name = "codigo_activo", unique = true, length = 50)
    private String codigoActivo;

    @Column(name = "codigo_adi", length = 50)
    private String codigoAdi;

    @Column(length = 255)
    private String descripcion;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_incorporacion")
    private Date fechaIncorporacion;

    @Column(name = "costo_inicial")
    private Double costoInicial;

    @Column(name = "valor_neto")
    private Double valorNeto;

    @Column(name = "estado_bien", length = 50)
    private String estadoBien;

    @Column(length = 255)
    private String observaciones;

    @Column(name = "nro_convenio", length = 50)
    private String nroConvenio;

    @Column(length = 50)
    private String rube;

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

    // Helpers para asignación directa por ID desde JSON plano
    @JsonProperty("responsableId")
    public void setResponsableId(Long id) {
        if (id != null) {
            Resp r = new Resp();
            r.setId(id);
            this.responsable = r;
        }
    }

    @JsonProperty("oficinaId")
    public void setOficinaId(Long id) {
        if (id != null) {
            Oficina o = new Oficina();
            o.setId(id);
            this.oficina = o;
        }
    }

    @JsonProperty("codcontId")
    public void setCodcontId(Long id) {
        if (id != null) {
            Codcont c = new Codcont();
            c.setId(id);
            this.codcont = c;
        }
    }

    @JsonProperty("auxiliarId")
    public void setAuxiliarId(Long id) {
        if (id != null) {
            Auxiliar a = new Auxiliar();
            a.setId(id);
            this.auxiliar = a;
        }
    }

    @JsonProperty("estadoId")
    public void setEstadoId(Long id) {
        if (id != null) {
            Estado e = new Estado();
            e.setId(id);
            this.estado = e;
        }
    }

    @JsonProperty("organismoFinId")
    public void setOrganismoFinId(Long id) {
        if (id != null) {
            OrganismoFin o = new OrganismoFin();
            o.setId(id);
            this.organismoFin = o;
        }
    }
}
