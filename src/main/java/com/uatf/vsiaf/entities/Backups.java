package com.uatf.vsiaf.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "backups_log")
public class Backups {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_backup")
    private Date fechaBackup;

    @Column(name = "nombre_archivo", length = 200)
    private String nombreArchivo;

    @Column(name = "usuario_crea", length = 50)
    private String usuarioCrea;
}