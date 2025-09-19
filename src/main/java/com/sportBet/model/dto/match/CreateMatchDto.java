package com.sportBet.model.dto.match;

import com.sportBet.model.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class CreateMatchDto {
    @Schema(example = "Champions League Final")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Schema(example = "2025-05-10")
    @NotNull(message = "Match date is required")
    private LocalDate matchDate;

    @Schema(example = "21:00")
    @NotNull(message = "Match time is required")
    private LocalTime matchTime;

    @Schema(example = "Barcelona")
    @NotBlank(message = "Team A must not be blank")
    private String teamA;

    @Schema(example = "Real Madrid")
    @NotBlank(message = "Team B must not be blank")
    private String teamB;

    @Schema(example = "FOOTBALL")
    @NotNull(message = "Sport is required")
    private Sport sport;
}
