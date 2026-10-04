package com.uatf.vsiaf.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class RevalDTO {
    private Long id;
    private Date fechaRevaluo;
    private Double valorNuevo;
    private Double vidaUtilNueva;
    private Long activoId;
}