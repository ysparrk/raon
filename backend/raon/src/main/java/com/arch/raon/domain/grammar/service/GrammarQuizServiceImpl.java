package com.arch.raon.domain.grammar.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GrammarQuizServiceImpl implements GrammarQuizService{

	private final GrammarQuizRepository grammarQuizRepository;

	@Override
	public List<GrammarQuiz> getQuizzes() {
		return grammarQuizRepository.random10();
	}
}
