package com.sportBet.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "match_odd",
        uniqueConstraints = {@UniqueConstraint(columnNames = {"match_id", "specifier"})}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MatchOdd {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String specifier;

    @Column(nullable = false)
    private Double odd;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

}
