package com.uatf.vsiaf.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class BajaDTO {
    private Long id;
    private Date fechaBaja;
    private String motivo;
    private String resolucion;
    private Long activoId;
}