package com.arch.raon.domain.grammar.repository;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;

import java.util.List;

public interface CustomGrammarRepository {
    // 전국 랭킹
    List<GrammarMyRankQueryDTO> findByCountry(Long memberId);
}
