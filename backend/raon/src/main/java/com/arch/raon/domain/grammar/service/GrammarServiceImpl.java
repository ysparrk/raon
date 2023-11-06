package com.arch.raon.domain.grammar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import com.arch.raon.domain.grammar.dto.request.GrammarResultDTO;
import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarMyRankingResDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.util.enums.GrammarRanking;
import com.arch.raon.global.util.enums.RankState;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
	private final MemberRepository memberRepository;

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
	public void saveScoreResult(GrammarResultSaveReqDTO grammarResultSaveReqDTO) {
		// // 1. member_id가 유효한지 확인
		// boolean isValidMember =
		//
		// // 2.
		final long TEST_MEMBER_ID = 777;
		int score = 0;
		Member member = memberRepository.findById(TEST_MEMBER_ID).get();
		List<GrammarResultDTO> grammarResultList = grammarResultSaveReqDTO.getGrammarResultList();
		for (GrammarResultDTO grammarResultDTO : grammarResultList) {
			if(grammarResultDTO.getHit() == 1) {
				score ++;
			}
		}

		GrammarScore grammarScoreEntity = GrammarScore
			.builder()
			.score(score)
			.member(member) // TODO: 현재 member_id가 없어서 임의로 저장.
			.build();

		 grammarScoreRepository.save(grammarScoreEntity);
	}

	@Transactional
	@Override
	public void updateStatistics(GrammarResultSaveReqDTO grammarResultSaveReqDTO) {
		// 문제별 정답율 업데이트
		List<GrammarResultDTO> grammarResultList = grammarResultSaveReqDTO.getGrammarResultList();
		for (GrammarResultDTO grammarResultDTO : grammarResultList) {
			GrammarQuiz grammarQuiz = grammarQuizRepository.findById(grammarResultDTO.getId()).get();
			if(grammarResultDTO.getHit() == 1) {
				grammarQuiz.quizCorrect(); // 정답 처리
			} else {
				grammarQuiz.quizNotCorrect(); // 오답 처리
			}
		}
	}

	@Override
	public List<GrammarMyRankQueryDTO> getMyRank(Long memberId, GrammarRanking grammarRanking) {
		/**
		 * 랭킹 조회
		 * 1. 유저가 1~5등 사이면 1~6등 보내주기
		 * 2. 그 밑이면 1~3등 / 유저 +-1 이랑 유저
		 */

		Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
			@Override
			public ErrorCode getErrorCode() {
				return super.getErrorCode();
			}
		});

		if (grammarRanking.equals(GrammarRanking.GRAMMAR_COUNTRY_MY)) {
			// 전국 랭킹 조회
			List<GrammarMyRankQueryDTO> allByCountry = grammarScoreRepository.findAllByCountry();

			// 순위 리스트에서 내 인덱스
			int myIdx = IntStream.range(0, allByCountry.size())
					.filter(i -> allByCountry.get(i).getNickname().equals(member.getNickname()))
					.findFirst()
					.orElse(-1);

			if (myIdx < 6) {
				// TOP3
			} else if (myIdx == allByCountry.size() - 1) {
				// LAST_PLACE
			} else {
				// MIDDLE_PLACE
				List<GrammarMyRankQueryDTO> middlePlaceRankResult = getMiddlePlaceRankResult(myIdx, allByCountry);

				GrammarMyRankingResDTO grammarMyRankingResDTO = GrammarMyRankingResDTO.builder()
						.myRank(myIdx+1)
						.myScore(middlePlaceRankResult.get(4).getScore())
						.rankState(RankState.MIDDLE_PLACE)
						.rankList(middlePlaceRankResult)
						.build();
			}



		} else if (grammarRanking.equals(GrammarRanking.GRAMMAR_SCHOOL_MY)) {
			// 교내 랭킹 조회

		}
		return null;
	}

	@Override
	public List<GrammarMyRankQueryDTO> getMiddlePlaceRankResult(int myIdx, List<GrammarMyRankQueryDTO> allByCountry) {

		List<GrammarMyRankQueryDTO> selectedRankResults = new ArrayList<>();

		// TOP3
		for (int i = 0; i < 3; i++) {
			if (i < allByCountry.size()) {
				GrammarMyRankQueryDTO baseDTO = allByCountry.get(i);
				selectedRankResults.add(new GrammarMyRankQueryDTO(i+1, baseDTO.getNickname(), baseDTO.getScore()));
			}

		}

		// member의 바로 위 한명, member, member의 바로 아래 한명
		for (int i = myIdx - 1; i <= myIdx + 1; i++) {
			if (i >= 0 && i < allByCountry.size()) {
				GrammarMyRankQueryDTO baseDTO = allByCountry.get(i);
				selectedRankResults.add(new GrammarMyRankQueryDTO(i+1, baseDTO.getNickname(), baseDTO.getScore()));
			}
		}

		return selectedRankResults;
	}


}
