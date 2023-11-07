package com.arch.raon.domain.dictionary.controller;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.service.DictionaryService;
import com.arch.raon.global.auth.dto.UserAuthentication;
import com.arch.raon.global.dto.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/dictionary")
@RestController
@RequiredArgsConstructor
public class DictionaryController {

    private final DictionaryService dictionaryService;

    @GetMapping("/quiz")
    public ResponseEntity<ResponseDTO> getQuizzes(
    ){
        DictionaryQuizResDTO dictionaryQuizzes = dictionaryService.getDictionaryQuizzes();

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("국어사전 퀴즈 리스트")
                        .data(dictionaryQuizzes)
                        .build());
    }

    @PostMapping("/single-result")
    public ResponseEntity<ResponseDTO> saveResult(
            @AuthenticationPrincipal UserAuthentication userAuth,
            @RequestBody DictionaryScoreReqDTO dictionaryScoreReqDTO
            ) {
        dictionaryService.saveDictionaryQuizResult(userAuth.getId(), dictionaryScoreReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("국어사전 퀴즈 결과 저장 성공")
                        .build());
    }
}
