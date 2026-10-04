package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class OficinaDTO {
    private Long id;
    private String codigoOficina;
    private String nombre;
    private Long unidadAdminId;
}