package com.sportBet.mapper;

import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
import com.sportBet.model.entity.Match;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MatchMapper {
    // --- Entity → DTO ---
    MatchDto toMatchDto(Match match);

    List<MatchDto> toMatchDtoList(List<Match> matches);

    // --- DTO → Entity ---
    Match toMatchEntity(CreateMatchDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateMatchFromDto(UpdateMatchDto dto, @MappingTarget Match match);
}
