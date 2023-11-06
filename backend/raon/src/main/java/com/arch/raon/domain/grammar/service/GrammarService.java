package com.arch.raon.domain.grammar.service;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarMyRankingResDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;

import java.util.List;

public interface GrammarService {
	List<GrammarQuizResDTO> getQuizzes();

	void saveScoreResult(GrammarResultSaveReqDTO grammarResultSaveReqDTO);
	void updateStatistics(GrammarResultSaveReqDTO grammarResultSaveReqDTO);

	List<GrammarMyRankQueryDTO> getMiddlePlaceRankResult(int myIdx, List<GrammarMyRankQueryDTO> allByCountry);
	List<GrammarMyRankQueryDTO> getTopPlaceRankResult(List<GrammarMyRankQueryDTO> allByCountry);
	List<GrammarMyRankQueryDTO> getLastPlaceRankResult(List<GrammarMyRankQueryDTO> allByCountry);
	GrammarMyRankingResDTO getMyCountryRank(Long memberId);

}
