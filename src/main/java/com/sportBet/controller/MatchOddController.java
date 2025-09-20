package com.sportBet.controller;

import com.sportBet.model.dto.matchodd.CreateMatchOddDto;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.dto.matchodd.UpdateMatchOddDto;
import com.sportBet.service.MatchOddService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matches/{matchId}/odds")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @GetMapping
    public ResponseEntity<List<MatchOddDto>> getOddsForMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchOddService.getOddsForMatch(matchId));
    }

    @GetMapping("/{oddId}")
    public ResponseEntity<MatchOddDto> getOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        return ResponseEntity.ok(matchOddService.getOdd(matchId, oddId));
    }

    @PostMapping
    public ResponseEntity<MatchOddDto> addOddToMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody CreateMatchOddDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchOddService.addOddToMatch(matchId, dto));
    }

    @PutMapping("/{oddId}")
    public ResponseEntity<MatchOddDto> updateOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId,
            @Valid @RequestBody UpdateMatchOddDto dto) {
        return ResponseEntity.ok(matchOddService.updateOdd(matchId, oddId, dto));
    }

    @DeleteMapping("/{oddId}")
    public ResponseEntity<Void> deleteOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        matchOddService.deleteOdd(matchId, oddId);
        return ResponseEntity.noContent().build();
    }

}
