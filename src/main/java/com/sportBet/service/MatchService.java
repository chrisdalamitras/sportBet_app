package com.sportBet.service;

import com.sportBet.exception.NotFoundException;
import com.sportBet.mapper.MatchMapper;
import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
import com.sportBet.model.entity.Match;
import com.sportBet.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {
    private final MatchRepository matchRepo;
    private final MatchMapper mapper;

    public MatchService(MatchRepository matchRepo, MatchMapper mapper) {
        this.matchRepo = matchRepo;
        this.mapper = mapper;
    }

    public List<MatchDto> getAllMatches() {
        return mapper.toMatchDtoList(matchRepo.findAll());
    }

    public MatchDto getMatch(Long id) {
        Match match = matchRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Match not found with id " + id));

        return mapper.toMatchDto(match);
    }

    public MatchDto createMatch(CreateMatchDto dto) {
        Match match = mapper.toMatchEntity(dto);
        return mapper.toMatchDto(matchRepo.save(match));
    }

    public MatchDto updateMatch(Long id, UpdateMatchDto dto) {
        Match match = matchRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Match not found with id " + id));

        mapper.updateMatchFromDto(dto, match);
        return mapper.toMatchDto(matchRepo.save(match));
    }

    public void deleteMatch(Long id) {
        Match match = matchRepo.findById(id)
                .orElseThrow(() -> new NotFoundException("Match not found with id " + id));

        matchRepo.delete(match);
    }

}
