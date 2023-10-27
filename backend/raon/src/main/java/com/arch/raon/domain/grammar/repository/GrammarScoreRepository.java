package com.arch.raon.domain.grammar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;

public interface GrammarScoreRepository extends JpaRepository<GrammarScore, Long>{

}
