package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class BajasDTO {
    private Long id;
    private String observacion;
    private Double montoHistorial;
    private Long bajaId;
    private Long activoId;
}
