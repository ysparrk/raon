package com.arch.raon.domain.grammar.controller;

import com.arch.raon.domain.grammar.dto.request.GrammarScoreReqDTO;
import com.arch.raon.domain.grammar.dto.response.GrammarQuizResDTO;
import com.arch.raon.domain.grammar.entity.GrammarQuiz;
import com.arch.raon.domain.grammar.service.GrammarService;
import com.arch.raon.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	@PostMapping("/score")
	public ResponseEntity<ResponseDTO> saveScore(@RequestBody GrammarScoreReqDTO grammarScoreReqDTO){


		grammarService.saveQuizResult(grammarScoreReqDTO);

		return ResponseEntity.status(HttpStatus.OK)
			.body(ResponseDTO.builder()
				.message("결과 저장 성공")
				.build());
	}

}
