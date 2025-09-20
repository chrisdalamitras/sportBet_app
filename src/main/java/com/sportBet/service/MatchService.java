package com.sportBet.service;

import com.sportBet.exception.NotFoundException;
import com.sportBet.mapper.MatchMapper;
import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
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
public class MatchService {
    private final MatchRepository matchRepo;
    private final MatchOddRepository oddRepo;
    private final MatchMapper mapper;

    public MatchService(MatchRepository matchRepo, MatchOddRepository oddRepo, MatchMapper mapper) {
        this.matchRepo = matchRepo;
        this.oddRepo = oddRepo;
        this.mapper = mapper;
    }

    // ---- Match methods ----
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

    // ---- Odds methods ----
    public List<MatchOddDto> getOddsForMatch(Long matchId) {
        return mapper.toMatchOddDtoList(oddRepo.findByMatchId(matchId));
    }

    public MatchOddDto getOdd(Long matchId, Long oddId) {
        MatchOdd odd = oddRepo.findById(oddId)
                .orElseThrow(() -> new NotFoundException("Odd not found with id " + oddId));

        if (!odd.getMatch().getId().equals(matchId)) {
            throw new RuntimeException("Odd does not belong to this match");
        }

        return mapper.toMatchOddDto(odd);
    }

    public MatchOddDto addOddToMatch(Long matchId, CreateMatchOddDto dto) {
        Match match = matchRepo.findById(matchId)
                .orElseThrow(() -> new NotFoundException("Match not found with id " + matchId));

        MatchOdd odd = mapper.toMatchOddEntity(dto);
        odd.setMatch(match);
        return mapper.toMatchOddDto(oddRepo.save(odd));
    }

    public MatchOddDto updateOdd(Long matchId, Long oddId, UpdateMatchOddDto dto) {
        MatchOdd odd = oddRepo.findById(oddId)
                .orElseThrow(() -> new NotFoundException("Odd not found with id " + oddId));

        if (!odd.getMatch().getId().equals(matchId)) {
            throw new RuntimeException("Odd does not belong to this match");
        }

        mapper.updateMatchOddFromDto(dto, odd);
        return mapper.toMatchOddDto(oddRepo.save(odd));
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
