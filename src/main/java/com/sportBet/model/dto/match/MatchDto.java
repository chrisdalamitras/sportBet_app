package com.sportBet.model.dto.match;

import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.enums.Sport;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class MatchDto {
    private Long id;
    private String description;
    private LocalDate matchDate;
    private LocalTime matchTime;
    private String teamA;
    private String teamB;
    private Sport sport;
    private List<MatchOddDto> odds;
}
