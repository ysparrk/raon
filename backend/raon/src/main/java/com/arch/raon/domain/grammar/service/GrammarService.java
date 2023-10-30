package com.arch.raon.domain.grammar.service;

import java.util.List;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDto;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;

public interface GrammarService {
	List<GrammarQuiz> getQuizzes();

	void saveQuizResult(GrammarScoreReqDto grammarScore);
}
