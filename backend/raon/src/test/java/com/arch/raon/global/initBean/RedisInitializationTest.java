package com.arch.raon.global.initBean;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;

@SpringBootTest
public class RedisInitializationTest {

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	@Autowired
	RedisInitialization redisInitialization;
	@Autowired
	GrammarQuizRepository grammarQuizRepository;

	@BeforeEach
	void beforeEach() {

	}

	@Test
	@Transactional
	public void testStrings() throws Exception {
		//given

		// when
		redisInitialization.loadData();
		ListOperations<String, Object> opsForList = redisTemplate.opsForList();
		GrammarQuiz result = (GrammarQuiz)opsForList.range("quizzes",4,4).get(0);
		System.out.println("result.toString() = " + result.toString());
		//then
		assertThat(result).isNotNull();
	}
}
