package com.uatf.vsiaf.dtos;
import lombok.Data;

@Data
public class PasswDTO {
    private Long id;
    private String username;
    private String password;
    private Boolean activo;
}