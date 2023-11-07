package com.arch.raon.domain.grammar.repository;

import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarScoreRepository extends JpaRepository<GrammarScore, Long>, CustomGrammarRepository{

}
