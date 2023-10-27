package com.arch.raon.global.initBean;

import java.util.List;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RedisInitialization {

	private final RedisTemplate<String, Object> redisTemplate;


	private final GrammarQuizRepository grammarQuizRepository;

	@PostConstruct
	public void loadData() {
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();

		if (Boolean.FALSE.equals(redisTemplate.hasKey("quizzes"))) {
			List<GrammarQuiz> quizzes = grammarQuizRepository.findAll();
			for(GrammarQuiz quiz : quizzes) {
				opsForList.leftPush("quizzes", quiz);
			}
		}
	}
}
