package com.sportBet.model.dto.error;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Schema(description = "Field-level validation error")
public class FieldErrorResponse {

    @Schema(description = "Field name", example = "team_a")
    private String field;

    @Schema(description = "Validation error message", example = "must not be blank")
    private String message;

}
