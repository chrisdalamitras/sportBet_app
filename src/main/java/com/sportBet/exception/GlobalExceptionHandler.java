package com.sportBet.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // --- Validation errors (e.g., @Valid) ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        List<Map<String, String>> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> Map.of(
                        "field", err.getField(),
                        "message", err.getDefaultMessage()
                ))
                .toList();

        body.put("errors", errors);
        body.put("message", "Validation failed");
        return ResponseEntity.badRequest().body(body);
    }

    // --- Invalid JSON / enum / date ---
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidFormat(HttpMessageNotReadableException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", HttpStatus.BAD_REQUEST.getReasonPhrase());

        Throwable cause = ex.getCause();
        if (cause instanceof com.fasterxml.jackson.databind.exc.InvalidFormatException formatEx) {
            if (formatEx.getTargetType().isEnum()) {
                Object[] allowed = formatEx.getTargetType().getEnumConstants();
                body.put("message", "Invalid value for enum field");
                body.put("invalidValue", formatEx.getValue());
                body.put("allowedValues", Arrays.stream(allowed).map(Object::toString).toList());
            } else if (formatEx.getTargetType().equals(LocalDate.class)) {
                body.put("message", "Invalid date format");
                body.put("invalidValue", formatEx.getValue());
                body.put("expectedFormat", "yyyy-MM-dd");
            } else if (formatEx.getTargetType().equals(LocalTime.class)) {
                body.put("message", "Invalid time format");
                body.put("invalidValue", formatEx.getValue());
                body.put("expectedFormat", "HH:mm:ss");
            }
        } else {
            body.put("message", "Malformed JSON or invalid field type");
            body.put("details", ex.getMessage());
        }

        return ResponseEntity.badRequest().body(body);
    }

    // --- Not found exception ---
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(NotFoundException ex) {
        Map<String, Object> body = Map.of(
                "status", HttpStatus.NOT_FOUND.value(),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", ex.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // --- Generic fallback ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleOtherExceptions(Exception ex) {
        Map<String, Object> body = Map.of(
                "status", HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                "message", ex.getMessage()
        );
        return ResponseEntity.internalServerError().body(body);
    }
}
