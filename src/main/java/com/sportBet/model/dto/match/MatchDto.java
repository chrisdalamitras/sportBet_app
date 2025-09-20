package com.sportBet.model.dto.match;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.enums.Sport;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchDto {
    private Long id;
    private String description;

    @Schema(type = "string", format = "date", example = "2025-07-01")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate matchDate;

    @Schema(type = "string", format = "time", example = "20:00")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime matchTime;

    private String team_a;
    private String team_b;
    private Sport sport;
    private List<MatchOddDto> odds;
}
