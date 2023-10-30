package com.arch.raon.domain.grammar.service;

import java.util.List;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDto;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;

public interface GrammarQuizService {
	List<GrammarQuiz> getQuizzes();

	void saveQuizResult(GrammarScoreReqDto grammarScore);
}
