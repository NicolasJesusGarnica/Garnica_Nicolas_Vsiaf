package com.uatf.vsiaf.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "usuarios_passw")
@Schema(description = "Entidad Password / Usuarios: Credenciales y accesos del sistema (VSIAF)")
public class Passw {

    @Id
    @io.swagger.v3.oas.annotations.media.Schema(description = "Identificador único (opcional para autoincremental, o manual para validar duplicados)", example = "1")
    private Long id;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Column(unique = true, length = 10)
    @Size(max = 10, message = "El username no puede exceder 10 caracteres (C10 en VSIAF)")
    @Schema(description = "Login / nombre de usuario único en el sistema (máximo 10 caracteres)", example = "admin")
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(length = 255)
    @Schema(description = "Contraseña de acceso al sistema", example = "admin123")
    private String password;

    @Column(name = "nombre_completo", length = 40)
    @Size(max = 40, message = "El nombre completo no puede exceder 40 caracteres (C40 en VSIAF)")
    @Schema(description = "Nombre y apellidos completos del funcionario", example = "Juan Perez Lopez")
    private String nombreCompleto;

    @Column(length = 50)
    @Size(max = 50, message = "La descripción no puede exceder 50 caracteres (C50 en VSIAF)")
    @Schema(description = "Descripción o notas del usuario", example = "Administrador de Activos Fijos")
    private String descripcion;

    @Column(length = 8)
    @Size(max = 8, message = "El grupo no puede exceder 8 caracteres ('Admin', 'User', 'VSIAF')")
    @Schema(description = "Grupo o rol de seguridad ('Admin', 'User', 'VSIAF')", example = "Admin")
    private String grupo;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Estado de habilitación del usuario (true por defecto)")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Boolean activo;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_ultimo_acceso")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Fecha del último acceso registrada automáticamente")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Date fechaUltimoAcceso;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    @Schema(accessMode = Schema.AccessMode.READ_ONLY, description = "Funcionario responsable institucional asociado al usuario")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Resp responsable;

    @JsonProperty("responsableId")
    @Schema(description = "ID del funcionario responsable asociado (FK a Resp)", example = "1")
    public void setResponsableId(Long id) {
        if (id != null) {
            Resp r = new Resp();
            r.setId(id);
            this.responsable = r;
        }
    }
}
