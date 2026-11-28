package com.uatf.vsiaf.dtos;

import lombok.Data;
import java.time.LocalDate;

@Data
public class AuxiliarDTO {
    private Long id;
    private String entidad;
    private String unidad;
    private String codigo;
    private String nombre;
    private String observaciones;
    private LocalDate feult;
    private String usuar;
    private Long objgastoId;
    private Long codcontId;
}
