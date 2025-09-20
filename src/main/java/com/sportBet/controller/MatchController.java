package com.sportBet.controller;

import com.sportBet.model.dto.match.CreateMatchDto;
import com.sportBet.model.dto.match.MatchDto;
import com.sportBet.model.dto.match.UpdateMatchDto;
import com.sportBet.service.MatchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Get all matches",
            description = "Retrieve a list of all matches.")
    @ApiResponse(
            responseCode = "200",
            description = "List of matches retrieved successfully",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class)))
    @GetMapping
    public ResponseEntity<List<MatchDto>> getAllMatches() {
        return ResponseEntity.ok(matchService.getAllMatches());
    }

    @Operation(
            summary = "Get match by ID",
            description = "Retrieve a single match by its ID.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Match retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found")})
    @GetMapping("/{matchId}")
    public ResponseEntity<MatchDto> getMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchService.getMatch(matchId));
    }

    @Operation(
            summary = "Create a new match",
            description = "Create a match with required fields.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Match created successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error")})
    @PostMapping
    public ResponseEntity<MatchDto> createMatch(@Valid @RequestBody CreateMatchDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchService.createMatch(dto));
    }

    @Operation(
            summary = "Update an existing match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Match updated successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MatchDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found")})
    @PutMapping("/{matchId}")
    public ResponseEntity<MatchDto> updateMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody UpdateMatchDto dto) {
        return ResponseEntity.ok(matchService.updateMatch(matchId, dto));
    }

    @Operation(
            summary = "Delete a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Match deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found")})
    @DeleteMapping("/{matchId}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long matchId) {
        matchService.deleteMatch(matchId);
        return ResponseEntity.noContent().build();
    }

}
