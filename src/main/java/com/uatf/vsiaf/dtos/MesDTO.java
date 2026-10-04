package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class MesDTO {
    private Long id;
    private Integer numeroMes;
    private String nombreMes;
    private Boolean cerrado;
}