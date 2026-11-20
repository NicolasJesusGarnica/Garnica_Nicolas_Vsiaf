package com.uatf.vsiaf.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponseMessage<T> {
    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String mensaje;
    private T data;

    public static <T> ApiResponseMessage<T> exito(int status, String mensaje, T data) {
        return ApiResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .mensaje(mensaje)
                .data(data)
                .build();
    }

    public static <T> ApiResponseMessage<T> error(int status, String mensaje) {
        return ApiResponseMessage.<T>builder()
                .timestamp(LocalDateTime.now())
                .status(status)
                .mensaje(mensaje)
                .build();
    }
}
