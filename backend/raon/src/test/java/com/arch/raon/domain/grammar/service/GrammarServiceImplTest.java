package com.arch.raon.domain.grammar.service;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import com.arch.raon.domain.grammar.dto.request.GrammarResultDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;
import com.arch.raon.domain.grammar.repository.GrammarScoreRepository;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class GrammarServiceImplTest {
	@Autowired
	GrammarQuizRepository grammarQuizRepository;

	@Autowired
	GrammarService grammarService;

	@Autowired
	GrammarScoreRepository grammarScoreRepository;

	@Autowired
	MemberRepository memberRepository;

	@Test
	void getQuizzes() {
		// given

		// when
		List<GrammarQuizResDTO> expectQuizzes = grammarService.getQuizzes();
		System.out.println("expectQuizzes = " + expectQuizzes);
		// then
		assertThat(10).isEqualTo(expectQuizzes.size());
	}

	@Test
	@Transactional
	void saveScoreResult() {
		// given -> 한 문제를 맞혔다고 가정
		List<GrammarResultDTO> grammarResultList = new ArrayList<>();
		GrammarResultDTO grammarResultDTO = GrammarResultDTO.builder()
				.id(1L)
				.hit(1).build();
		grammarResultList.add(grammarResultDTO);
		GrammarResultSaveReqDTO grammarResultSaveReqDTO = GrammarResultSaveReqDTO.builder()
				.grammarResultList(grammarResultList).build();

		// when
		grammarService.saveScoreResult(grammarResultSaveReqDTO);

		// then -> 맞힌 횟수가 증가 했는지
		Member member = memberRepository.findById(777L).get();
		GrammarScore scoreResult = grammarScoreRepository.findByMember(member);
		assertThat(scoreResult.getScore()).isEqualTo(1);
	}

	@Test
	@Transactional
	void updateStatistics() {
		// given -> 1번 문제를 맞혔다고 가정
		List<GrammarResultDTO> grammarResultList = new ArrayList<>();
		GrammarResultDTO grammarResultDTO = GrammarResultDTO.builder()
				.id(1L)
				.hit(1).build();
		grammarResultList.add(grammarResultDTO);
		GrammarResultSaveReqDTO grammarResultSaveReqDTO = GrammarResultSaveReqDTO.builder()
				.grammarResultList(grammarResultList).build();

		// when
		GrammarQuiz grammarQuiz = grammarQuizRepository.findById(1L).get();
		int originalHit = grammarQuiz.getHit();
		grammarService.updateStatistics(grammarResultSaveReqDTO);
		int updatedHit = grammarQuiz.getHit();

		// then -> 맞힌 횟수가 증가 했는지
		assertThat(originalHit + 1).isEqualTo(updatedHit);
	}

}