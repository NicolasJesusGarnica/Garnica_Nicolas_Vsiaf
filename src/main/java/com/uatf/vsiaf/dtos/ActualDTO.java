package com.uatf.vsiaf.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class ActualDTO {
    private Long id;
    private String codigoActivo;
    private String descripcion;
    private Date fechaIncorporacion;
    private Double costoInicial;
    private Double valorNeto;
    private String estadoBien;
    private Long responsableId;
    private Long oficinaId;
    private Long codcontId;
    private Long auxiliarId;
    private Long estadoId;
    private Long organismoFinId;
}
