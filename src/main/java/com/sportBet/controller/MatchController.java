package com.sportBet.controller;

import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
import com.sportBet.model.dto.matchodd.CreateMatchOddDto;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.dto.matchodd.UpdateMatchOddDto;
import com.sportBet.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches")
public class MatchController {

    private final MatchService matchService;

    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    // ---- Match endpoints ----
    @GetMapping
    public List<MatchDto> getAllMatches() {
        return matchService.getAllMatches();
    }

    @GetMapping("/{matchId}")
    public MatchDto getMatch(@PathVariable Long matchId) {
        return matchService.getMatch(matchId);
    }

    @PostMapping
    public MatchDto createMatch(@Valid @RequestBody CreateMatchDto dto) {
        return matchService.createMatch(dto);
    }

    @PutMapping("/{matchId}")
    public MatchDto updateMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody UpdateMatchDto dto) {
        return matchService.updateMatch(matchId, dto);
    }

    @DeleteMapping("/{matchId}")
    public void deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
    }

    // ---- MatchOdd endpoints ----
    @GetMapping("/{matchId}/odds")
    public List<MatchOddDto> getOddsForMatch(@PathVariable Long matchId) {
        return matchService.getOddsForMatch(matchId);
    }

    @GetMapping("/{matchId}/odds/{oddId}")
    public MatchOddDto getOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        return matchService.getOdd(matchId, oddId);
    }

    @PostMapping("/{matchId}/odds")
    public MatchOddDto addOddToMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody CreateMatchOddDto dto) {
        return matchService.addOddToMatch(matchId, dto);
    }

    @PutMapping("/{matchId}/odds/{oddId}")
    public MatchOddDto updateOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId,
            @Valid @RequestBody UpdateMatchOddDto dto) {
        return matchService.updateOdd(matchId, oddId, dto);
    }

    @DeleteMapping("/{matchId}/odds/{oddId}")
    public void deleteOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        matchService.deleteOdd(matchId, oddId);
    }

}
