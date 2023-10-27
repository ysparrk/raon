package com.arch.raon.domain.grammar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;

@SpringBootTest
class GrammarQuizServiceImplTest {
	@Autowired
	GrammarQuizRepository grammarQuizRepository;
	@Autowired
	GrammarQuizService grammarQuizService;

	@Test
	void getQuizzes() {
		// given

		// when
		List<GrammarQuiz> expectQuizzes = grammarQuizService.getQuizzes();
		System.out.println("expectQuizzes = " + expectQuizzes);
		// then
		assertThat(10).isEqualTo(expectQuizzes.size());
	}
}