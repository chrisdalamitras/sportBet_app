package com.sportBet.model.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sportBet.model.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMatchDto {
    @Schema(example = "Champions League Final")
    @NotBlank(message = "Description must not be blank")
    private String description;

    @Schema(type = "string", format = "date", example = "2025-07-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @NotNull(message = "Match date is required")
    private LocalDate matchDate;

    @Schema(type = "string", format = "time", example = "20:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    @NotNull(message = "Match time is required")
    private LocalTime matchTime;

    @Schema(example = "Barcelona")
    @NotBlank(message = "Team A must not be blank")
    private String team_a;

    @Schema(example = "Real Madrid")
    @NotBlank(message = "Team B must not be blank")
    private String team_b;

    @Schema(example = "FOOTBALL")
    @NotNull(message = "Sport is required")
    private Sport sport;
}
