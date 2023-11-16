package com.arch.raon.domain.grammar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;



public interface GrammarQuizRepository extends JpaRepository<GrammarQuiz, Long> {
	@Query(nativeQuery = true, value = "SELECT * FROM grammar_quiz ORDER BY RAND() LIMIT 10")
	List<GrammarQuiz> random10();
}
