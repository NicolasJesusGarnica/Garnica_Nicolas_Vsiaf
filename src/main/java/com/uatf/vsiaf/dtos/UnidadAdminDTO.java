package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class UnidadAdminDTO {
    private Long id;
    private String codigoUnidad;
    private String nombre;
    private String ciudad;
    private Long entidadId;
}