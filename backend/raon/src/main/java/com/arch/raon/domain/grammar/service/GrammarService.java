package com.arch.raon.domain.grammar.service;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.global.util.enums.GrammarRanking;

import java.util.List;

public interface GrammarService {
	List<GrammarQuizResDTO> getQuizzes();

	void saveScoreResult(GrammarResultSaveReqDTO grammarResultSaveReqDTO);
	void updateStatistics(GrammarResultSaveReqDTO grammarResultSaveReqDTO);

	List<GrammarMyRankQueryDTO> getMyRank(Long memberId, GrammarRanking grammarRanking);
	List<GrammarMyRankQueryDTO> getMiddlePlaceRankResult(int myIndex, List<GrammarMyRankQueryDTO> allByCountry);
}
