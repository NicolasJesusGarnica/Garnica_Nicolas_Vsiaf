package com.uatf.vsiaf.dtos;

import lombok.Data;

@Data
public class CodcontDTO {
    private Long id;
    private String cuentaContable;
    private String descripcion;
    private Integer vidaUtilAnios;
    private Double coeficienteDepreciacion;
    private Boolean deprecia;
    private Boolean actualiza;
    private String observaciones;
}
