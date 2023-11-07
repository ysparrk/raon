package com.arch.raon.domain.grammar.controller;

import com.arch.raon.domain.grammar.dto.request.GrammarResultSaveReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarMyRankingResDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.domain.grammar.service.GrammarService;
import com.arch.raon.global.auth.dto.UserAuthentication;
import com.arch.raon.global.dto.ResponseDTO;
import com.arch.raon.global.util.enums.GrammarRanking;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/grammar")
@RestController
@RequiredArgsConstructor
public class GrammarController {
	private final GrammarService grammarService;

	@GetMapping ("/quiz")
	public ResponseEntity<ResponseDTO> getQuizzes(
//		@AuthenticationPrincipal Long memberId
	){
		List<GrammarQuizResDTO> quizzes = grammarService.getQuizzes();

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDTO.builder()
				.message("맞춤법 퀴즈 리스트")
				.data(quizzes)
				.build());
	}

	@PostMapping("/result")
	public ResponseEntity<ResponseDTO> saveResult(
			@AuthenticationPrincipal UserAuthentication userAuth,
			@RequestBody GrammarResultSaveReqDTO grammarResultSaveReqDTO){
		grammarService.saveScoreResult(grammarResultSaveReqDTO, userAuth.getId()); // 해당 판의 점수를 저장(0~10)
		grammarService.updateStatistics(grammarResultSaveReqDTO); // 문제의 정답률을 업데이트

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDTO.builder()
				.message("결과 저장 성공")
				.build());
	}

	@GetMapping("/ranking/{grammarRanking}")
	public ResponseEntity<ResponseDTO> getMyRank(
			@AuthenticationPrincipal UserAuthentication userAuth,
			@PathVariable GrammarRanking grammarRanking
			) {
		GrammarMyRankingResDTO myRank = grammarService.getMyRank(userAuth.getId(), grammarRanking);

		if (grammarRanking.equals(GrammarRanking.GRAMMAR_COUNTRY_MY)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseDTO.builder()
							.message("맞춤법 퀴즈 전국 나의 랭킹")
							.data(myRank)
							.build());

		} else if (grammarRanking.equals(GrammarRanking.GRAMMAR_SCHOOL_MY)) {
			return ResponseEntity.status(HttpStatus.OK)
					.body(ResponseDTO.builder()
							.message("맞춤법 퀴즈 교내 나의 랭킹")
							.data(myRank)
							.build());
		} else {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(ResponseDTO.builder()
							.message("해당하는 랭킹 카테고리가 없습니다.")
							.build());
		}
	}

}
