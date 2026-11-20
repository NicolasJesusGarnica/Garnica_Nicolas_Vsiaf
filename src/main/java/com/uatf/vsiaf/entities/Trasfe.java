package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "transferencias")
public class Trasfe {
    @Id
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

    @JsonProperty("activoId")
    public void setActivoId(Long id) {
        if (id != null) {
            Actual a = new Actual();
            a.setId(id);
            this.activo = a;
        }
    }

    @JsonProperty("respOrigenId")
    public void setRespOrigenId(Long id) {
        if (id != null) {
            Resp r = new Resp();
            r.setId(id);
            this.respOrigen = r;
        }
    }

    @JsonProperty("respDestinoId")
    public void setRespDestinoId(Long id) {
        if (id != null) {
            Resp r = new Resp();
            r.setId(id);
            this.respDestino = r;
        }
    }
}
