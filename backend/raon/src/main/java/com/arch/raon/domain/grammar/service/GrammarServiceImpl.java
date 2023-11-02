package com.arch.raon.domain.grammar.service;

import java.util.ArrayList;
import java.util.List;

import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDTO;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.grammar.repository.GrammarQuizRepository;
import com.arch.raon.domain.grammar.repository.GrammarScoreRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class GrammarServiceImpl implements GrammarService {

	private final GrammarQuizRepository grammarQuizRepository;
	private final GrammarScoreRepository grammarScoreRepository;

	@Override
	public List<GrammarQuizResDTO> getQuizzes() {

		List<GrammarQuiz> quizList = grammarQuizRepository.random10();
		List<GrammarQuizResDTO> quizResDTOList = new ArrayList<>();

		// DTO 변환
		for (GrammarQuiz quiz : quizList) {

			// 푼 사람이 없을 경우 50 퍼센트로 가정
			int answer_percent = 50;
			if(quiz.getSubmit() != 0){
				answer_percent = quiz.getHit() * 100 / quiz.getSubmit();
			}

			GrammarQuizResDTO quizResDTO = GrammarQuizResDTO.builder()
					.id(quiz.getId())
					.content(quiz.getContent())
					.option_one(quiz.getOption_one())
					.option_two(quiz.getOption_two())
					.answer(quiz.getAnswer())
					.answer_percent(answer_percent).build();
			quizResDTOList.add(quizResDTO);
		}

		return quizResDTOList;
	}


	@Transactional
	@Override
	public void saveQuizResult(GrammarScoreReqDTO grammarScoreReqDTO) {
		// // 1. member_id가 유효한지 확인
		// boolean isValidMember =

		//
		// // 2.
		final long TEST_MEMBER_ID = 6;

		GrammarScore grammarScoreEntity = GrammarScore
			.builder()
			.score(grammarScoreReqDTO.getScore())
			.play_time(grammarScoreReqDTO.getPlay_time())
			.member_id(TEST_MEMBER_ID) // TODO: 현재 member_id가 없어서 임의로 저장.
			.build();

		 grammarScoreRepository.save(grammarScoreEntity);
	}
}
