package com.sportBet.repository;

import com.sportBet.model.entity.MatchOdd;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatchOddRepository extends JpaRepository<MatchOdd, Long> {
    List<MatchOdd> findByMatchId(Long matchId);
}
