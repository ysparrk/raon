package com.arch.raon.domain.grammar.service;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.query.GrammarRankQueryDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarMyRankingResDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.global.util.enums.GrammarRanking;

import java.util.List;

public interface GrammarService {
	List<GrammarQuizResDTO> getQuizzes();

	void saveScoreResult(GrammarResultSaveReqDTO grammarResultSaveReqDTO, Long id);
	void updateStatistics(GrammarResultSaveReqDTO grammarResultSaveReqDTO);

//	List<GrammarMyRankQueryDTO> getMiddlePlaceRankResult(int myIdx, List<GrammarMyRankQueryDTO> allRanks);
//	List<GrammarMyRankQueryDTO> getTopPlaceRankResult(List<GrammarMyRankQueryDTO> allByCountry);
//	List<GrammarMyRankQueryDTO> getLastPlaceRankResult(List<GrammarMyRankQueryDTO> allByCountry);
//	GrammarMyRankingResDTO getMyRankByGrammarRanking(Long memberId, List<GrammarMyRankQueryDTO> rankList);
//	GrammarMyRankingResDTO getMyRank(Long memberId, GrammarRanking grammarRanking);
	// 레디스 적용 부분
	List<GrammarMyRankQueryDTO> getMiddleRankResult(Long myRank, List<GrammarRankQueryDTO> allRanks);
	List<GrammarMyRankQueryDTO> getTopRankResult(List<GrammarRankQueryDTO> allRanks);
	List<GrammarMyRankQueryDTO> getLastRankResult(List<GrammarRankQueryDTO> allRanks);
	GrammarMyRankingResDTO getRankByGrammarRanking(Long memberId, List<GrammarMyRankQueryDTO> rankList);
	GrammarMyRankingResDTO getRank(Long memberId, GrammarRanking grammarRanking);
}
