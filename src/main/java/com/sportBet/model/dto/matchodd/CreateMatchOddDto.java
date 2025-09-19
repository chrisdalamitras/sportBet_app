package com.sportBet.model.dto.matchodd;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchOddDto {
    @Schema(example = "X")
    @NotBlank(message = "Specifier must not be blank")
    private String specifier;

    @Schema(example = "1.75")
    @NotNull(message = "Odd must not be null")
    @Positive(message = "Odd must be positive")
    private Double odd;
}
