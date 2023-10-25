package com.arch.raon.domain.grammar.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;

public interface GrammarRepository extends JpaRepository<GrammarQuiz, Long> {

}
