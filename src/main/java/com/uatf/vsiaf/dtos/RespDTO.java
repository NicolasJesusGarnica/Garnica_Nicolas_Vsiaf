package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class RespDTO {
    private Long id;
    private String ci;
    private String nombres;
    private String apellidos;
    private String cargo;
    private Long oficinaId;
}