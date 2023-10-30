package com.arch.raon.domain.grammar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDTO;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;
import com.arch.raon.domain.grammar.repository.GrammarScoreRepository;

@SpringBootTest
class GrammarServiceImplTest {
	@Autowired
	GrammarQuizRepository grammarQuizRepository;

	@Autowired
	GrammarService grammarService;

	@Autowired
	GrammarScoreRepository grammarScoreRepository;


	@Test
	void getQuizzes() {
		// given

		// when
		List<GrammarQuiz> expectQuizzes = grammarService.getQuizzes();
		System.out.println("expectQuizzes = " + expectQuizzes);
		// then
		assertThat(10).isEqualTo(expectQuizzes.size());
	}

	@Test
	void saveQuizResult() {
		//given
		GrammarScoreReqDTO grammarScore = GrammarScoreReqDTO.builder()
			.score(10)
			.play_time(1627)
			.build();


		//when
		grammarService.saveQuizResult(grammarScore);

		//then
	}
}