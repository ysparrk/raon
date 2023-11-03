package com.arch.raon.domain.grammar.service;

import java.util.List;

import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;

public interface GrammarService {
	List<GrammarQuizResDTO> getQuizzes();

	void saveScoreResult(GrammarResultSaveReqDTO grammarResultSaveReqDTO);
	void updateStatistics(GrammarResultSaveReqDTO grammarResultSaveReqDTO);
}
