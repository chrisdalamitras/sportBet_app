package com.sportBet.model.dto.matchodd;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOddDto {
    private Long id;
    private String specifier;
    private Double odd;
}
