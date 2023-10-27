package com.arch.raon.domain.grammar.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDto;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.entity.GrammarScore;
import com.arch.raon.domain.grammar.service.GrammarQuizService;
import com.arch.raon.global.dto.ResponseDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/grammar")
@RestController
@RequiredArgsConstructor
public class GrammarController {
	private final GrammarQuizService grammarQuizService;

	@GetMapping ("/quiz")
	public ResponseEntity<ResponseDto> getQuizzes(
		@AuthenticationPrincipal Long memberId
	){
		List<GrammarQuiz> quizzes = grammarQuizService.getQuizzes();

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.message("맞춤법 퀴즈 리스트")
				.data(quizzes)
				.build());
	}

	@PostMapping("/score")
	public ResponseEntity<ResponseDto> saveScore(@RequestBody GrammarScoreReqDto grammarScoreReqDto){


		grammarQuizService.saveQuizResult(grammarScoreReqDto);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDto.builder()
				.message("결과 저장 성공")
				.build());
	}

}
