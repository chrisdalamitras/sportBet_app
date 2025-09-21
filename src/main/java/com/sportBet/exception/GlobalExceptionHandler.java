package com.sportBet.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.sportBet.model.dto.error.ErrorResponse;
import com.sportBet.model.dto.error.FieldErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // --- Validation errors (e.g., @Valid) ---
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<FieldErrorResponse> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new FieldErrorResponse(err.getField(), err.getDefaultMessage()))
                .toList();

        ErrorResponse body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Validation failed");
        body.setErrors(errors);

        return ResponseEntity.badRequest().body(body);
    }

    // --- Invalid JSON / enum / date ---
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleInvalidFormat(HttpMessageNotReadableException ex) {

        ErrorResponse body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Malformed JSON or invalid field type");

        Throwable cause = ex.getCause();
        if (cause instanceof InvalidFormatException formatEx) {
            if (formatEx.getTargetType().isEnum()) {
                Object[] allowed = formatEx.getTargetType().getEnumConstants();
                body.setMessage("Invalid value for enum field");
                body.setInvalidValue(formatEx.getValue());
                body.setAllowedValues(Arrays.stream(allowed).map(Object::toString).toList());
            } else if (formatEx.getTargetType().equals(LocalDate.class)) {
                body.setMessage("Invalid date format");
                body.setInvalidValue(formatEx.getValue());
                body.setExpectedFormat("yyyy-MM-dd");
            } else if (formatEx.getTargetType().equals(LocalTime.class)) {
                body.setMessage("Invalid time format");
                body.setInvalidValue(formatEx.getValue());
                body.setExpectedFormat("HH:mm");
            }
        }

        return ResponseEntity.badRequest().body(body);
    }

    // --- Not found exception ---
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFound(NotFoundException ex) {
        ErrorResponse body = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolation(DataIntegrityViolationException ex) {

        ErrorResponse body = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                "Duplicate MatchOdd: a match cannot have the same specifier twice");

        return ResponseEntity.badRequest().body(body);
    }

    // --- Generic fallback ---
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOtherExceptions(Exception ex) {
        ErrorResponse body = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                ex.getMessage());
        return ResponseEntity.internalServerError().body(body);
    }

}
