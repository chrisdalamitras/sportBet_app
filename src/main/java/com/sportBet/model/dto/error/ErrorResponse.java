package com.sportBet.model.dto.error;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Standard error response")
public class ErrorResponse {

    @Schema(description = "HTTP status code", example = "400")
    private int status;

    @Schema(description = "Error type", example = "Bad Request")
    private String error;

    @Schema(description = "Description of error", example = "Invalid value for enum field")
    private String message;

    @Schema(description = "Invalid value provided by user")
    private Object invalidValue;

    @Schema(description = "Allowed values if applicable")
    private List<String> allowedValues;

    @Schema(description = "Expected format if applicable", example = "yyyy-MM-dd")
    private String expectedFormat;

    @Schema(description = "Detailed validation errors (field-level)")
    private List<FieldErrorResponse> errors;

    public ErrorResponse(int status, String error, String message) {
        this.status = status;
        this.error = error;
        this.message = message;
    }

}
