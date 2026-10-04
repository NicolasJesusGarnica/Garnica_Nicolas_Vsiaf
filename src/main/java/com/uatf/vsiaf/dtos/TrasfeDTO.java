package com.uatf.vsiaf.dtos;
import lombok.Data;
import java.util.Date;

@Data
public class TrasfeDTO {
    private Long id;
    private Date fechaTrasfe;
    private String motivo;
    private Long activoId;
    private Long respOrigenId;
    private Long respDestinoId;
}