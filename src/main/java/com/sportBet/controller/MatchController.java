package com.sportBet.controller;

import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
import com.sportBet.model.dto.matchodd.CreateMatchOddDto;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.dto.matchodd.UpdateMatchOddDto;
import com.sportBet.service.MatchService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatch(matchId));
    }

    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody CreateMatchDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(dto));
    }

    @PutMapping("/{matchId}")
    public ResponseEntity<MatchDto> updateMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody UpdateMatchDto dto) {
        return ResponseEntity.ok(matchService.updateMatch(matchId, dto));
    }

    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }

    // ---- MatchOdd endpoints ----
    @GetMapping("/{matchId}/odds")
    public ResponseEntity<List<MatchOddDto>> getOddsForMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getOddsForMatch(matchId));
    }

    @GetMapping("/{matchId}/odds/{oddId}")
    public ResponseEntity<MatchOddDto> getOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        return ResponseEntity.ok(matchService.getOdd(matchId, oddId));
    }

    @PostMapping("/{matchId}/odds")
    public ResponseEntity<MatchOddDto> addOddToMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody CreateMatchOddDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.addOddToMatch(matchId, dto));
    }

    @PutMapping("/{matchId}/odds/{oddId}")
    public ResponseEntity<MatchOddDto> updateOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId,
            @Valid @RequestBody UpdateMatchOddDto dto) {
        return ResponseEntity.ok(matchService.updateOdd(matchId, oddId, dto));
    }

    @DeleteMapping("/{matchId}/odds/{oddId}")
    public ResponseEntity<Void> deleteOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        matchService.deleteOdd(matchId, oddId);
        return ResponseEntity.noContent().build();
    }

}
