package com.arch.raon.domain.grammar.service;

import static org.assertj.core.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.Local;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDto;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;
import com.arch.raon.domain.grammar.repository.GrammarScoreRepository;

@SpringBootTest
class GrammarQuizServiceImplTest {
	@Autowired
	GrammarQuizRepository grammarQuizRepository;

	@Autowired
	GrammarQuizService grammarQuizService;

	@Autowired
	GrammarScoreRepository grammarScoreRepository;


	@Test
	void getQuizzes() {
		// given

		// when
		List<GrammarQuiz> expectQuizzes = grammarQuizService.getQuizzes();
		System.out.println("expectQuizzes = " + expectQuizzes);
		// then
		assertThat(10).isEqualTo(expectQuizzes.size());
	}

	@Test
	void saveQuizResult() {
		//given
		GrammarScoreReqDto grammarScore = GrammarScoreReqDto.builder()
			.score(10)
			.play_time(1627)
			.build();


		//when
		grammarQuizService.saveQuizResult(grammarScore);

		//then
	}
}