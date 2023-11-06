package com.arch.raon.domain.grammar.repository;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.member.entity.Member;

import java.util.List;

public interface CustomGrammarRepository {
    // 전국 랭킹
    List<GrammarMyRankQueryDTO> findAllByCountry();
    List<GrammarMyRankQueryDTO> findAllBySchool(Member member);
}
