package com.uatf.vsiaf.controllers;

import com.uatf.vsiaf.dtos.ApiResponseMessage;
import com.uatf.vsiaf.exceptions.BusinessRuleException;
import com.uatf.vsiaf.exceptions.DuplicateResourceException;
import com.uatf.vsiaf.exceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponseMessage<Void>> handleDuplicate(DuplicateResourceException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponseMessage.error(HttpStatus.CONFLICT.value(), ex.getMessage()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseMessage<Void>> handleNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponseMessage.error(HttpStatus.NOT_FOUND.value(), ex.getMessage()));
    }

    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ApiResponseMessage<Void>> handleBusinessRule(BusinessRuleException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ApiResponseMessage.error(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponseMessage<Void>> handleDataIntegrity(DataIntegrityViolationException ex) {
        String mensaje = "Conflicto de integridad de datos: el registro no puede ser eliminado o insertado porque tiene registros asociados o infringe una restricción de clave foránea/única en la base de datos.";
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(ApiResponseMessage.error(HttpStatus.CONFLICT.value(), mensaje));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponseMessage<Void>> handleGeneral(Exception ex) {
        String msg = ex.getMessage() != null ? ex.getMessage() : "";
        if (msg.contains("violates foreign key constraint") || msg.contains("is still referenced") || msg.contains("ConstraintViolationException")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(ApiResponseMessage.error(HttpStatus.CONFLICT.value(),
                            "No se puede eliminar el registro porque tiene datos o activos asociados en otra tabla (restricción de integridad referencial)."));
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ApiResponseMessage.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error interno en el servidor: " + msg));
    }
}
