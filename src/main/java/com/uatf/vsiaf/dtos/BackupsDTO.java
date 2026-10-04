package com.uatf.vsiaf.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class BackupsDTO {
    private Long id;
    private Date fechaBackup;
    private String nombreArchivo;
    private String usuarioCrea;
}