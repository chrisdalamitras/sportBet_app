package com.sportBet.controller;

import com.sportBet.model.dto.error.ErrorResponse;
import com.sportBet.model.dto.matchodd.CreateMatchOddDto;
import com.sportBet.model.dto.matchodd.MatchOddDto;
import com.sportBet.model.dto.matchodd.UpdateMatchOddDto;
import com.sportBet.service.MatchOddService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@RequestMapping("/api/matches/{matchId}/odds")
public class MatchOddController {

    private final MatchOddService matchOddService;

    public MatchOddController(MatchOddService matchOddService) {
        this.matchOddService = matchOddService;
    }

    @Operation(
            summary = "Get all odds for a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "List of odds retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MatchOddDto.class)))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Match not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping
    public ResponseEntity<List<MatchOddDto>> getOddsForMatch(@PathVariable Long matchId) {
        return ResponseEntity.ok(matchOddService.getOddsForMatch(matchId));
    }

    @Operation(
            summary = "Get specific odd by ID for a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Odd retrieved successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddDto.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{oddId}")
    public ResponseEntity<MatchOddDto> getOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        return ResponseEntity.ok(matchOddService.getOdd(matchId, oddId));
    }

    @Operation(
            summary = "Create a new odd for a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Odd created successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error or duplicate specifier",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public ResponseEntity<MatchOddDto> addOddToMatch(
            @PathVariable Long matchId,
            @Valid @RequestBody CreateMatchOddDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(matchOddService.addOddToMatch(matchId, dto));
    }

    @Operation(
            summary = "Update an odd for a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Odd updated successfully",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = MatchOddDto.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Validation error or duplicate specifier",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PutMapping("/{oddId}")
    public ResponseEntity<MatchOddDto> updateOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId,
            @Valid @RequestBody UpdateMatchOddDto dto) {
        return ResponseEntity.ok(matchOddService.updateOdd(matchId, oddId, dto));
    }

    @Operation(
            summary = "Delete an odd for a match")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Odd deleted successfully"),
            @ApiResponse(
                    responseCode = "404",
                    description = "Odd not found",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Odd does not belong to this match",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @DeleteMapping("/{oddId}")
    public ResponseEntity<Void> deleteOdd(
            @PathVariable Long matchId,
            @PathVariable Long oddId) {
        matchOddService.deleteOdd(matchId, oddId);
        return ResponseEntity.noContent().build();
    }

}
