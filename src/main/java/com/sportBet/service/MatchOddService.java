package com.sportBet.service;

import com.sportBet.exception.NotFoundException;
import com.sportBet.mapper.MatchOddMapper;
import com.sportBet.model.dto.matchodd.CreateMatchOddDto;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.dto.matchodd.UpdateMatchOddDto;
import com.sportBet.model.entity.Match;
import com.sportBet.model.entity.MatchOdd;
import com.sportBet.repository.MatchOddRepository;
import com.sportBet.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchOddService {
    private final MatchRepository matchRepo;
    private final MatchOddRepository oddRepo;
    private final MatchOddMapper matchOddMapper;

    public MatchOddService(MatchRepository matchRepo, MatchOddRepository oddRepo, MatchOddMapper matchOddMapper) {
        this.matchRepo = matchRepo;
        this.oddRepo = oddRepo;
        this.matchOddMapper = matchOddMapper;
    }

    public List<MatchOddDto> getOddsForMatch(Long matchId) {
        return matchOddMapper.toMatchOddDtoList(oddRepo.findByMatchId(matchId));
    }

    public MatchOddDto getOdd(Long matchId, Long oddId) {
        MatchOdd odd = oddRepo.findById(oddId)
                .orElseThrow(() -> new NotFoundException("Odd not found with id " + oddId));

        if (!odd.getMatch().getId().equals(matchId)) {
            throw new RuntimeException("Odd does not belong to this match");
        }

        return matchOddMapper.toMatchOddDto(odd);
    }

    public MatchOddDto addOddToMatch(Long matchId, CreateMatchOddDto dto) {
        Match match = matchRepo.findById(matchId)
                .orElseThrow(() -> new NotFoundException("Match not found with id " + matchId));

        MatchOdd odd = matchOddMapper.toMatchOddEntity(dto);
        odd.setMatch(match);
        return matchOddMapper.toMatchOddDto(oddRepo.save(odd));
    }

    public MatchOddDto updateOdd(Long matchId, Long oddId, UpdateMatchOddDto dto) {
        MatchOdd odd = oddRepo.findById(oddId)
                .orElseThrow(() -> new NotFoundException("Odd not found with id " + oddId));

        if (!odd.getMatch().getId().equals(matchId)) {
            throw new RuntimeException("Odd does not belong to this match");
        }

        matchOddMapper.updateMatchOddFromDto(dto, odd);
        return matchOddMapper.toMatchOddDto(oddRepo.save(odd));
    }

    public void deleteOdd(Long matchId, Long oddId) {
        MatchOdd odd = oddRepo.findById(oddId)
                .orElseThrow(() -> new NotFoundException("Odd not found with id" + oddId));

        if (!odd.getMatch().getId().equals(matchId)) {
            throw new RuntimeException("Odd does not belong to this match");
        }

        oddRepo.delete(odd);
    }

}
