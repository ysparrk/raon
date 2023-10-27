package com.arch.raon.domain.grammar.service;

import java.util.List;

import com.arch.raon.domain.grammar.dto.request.GrammarReqDto;
import com.arch.raon.domain.grammar.entity.GrammarScore;

public interface GrammarService {
	List<GrammarReqDto> getQuizzes();

}
