package com.arch.raon.domain.dictionary.controller;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDto;
import com.arch.raon.domain.dictionary.service.DictionaryService;
import com.arch.raon.global.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/dictionary")
@RestController
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/quiz")
    public ResponseEntity<ResponseDto> getQuizzes(
//            @AuthenticationPrincipal Long memberId
    ){
        DictionaryQuizResDto dictionaryQuizzes = dictionaryService.getDictionaryQuizzes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDto.builder()
                        .message("국어사전 퀴즈 리스트")
                        .data(dictionaryQuizzes)
                        .build());
    }
}
