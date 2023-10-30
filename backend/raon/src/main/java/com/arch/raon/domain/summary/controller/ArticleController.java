package com.arch.raon.domain.summary.controller;

import com.arch.raon.domain.summary.dto.response.ArticleResponseDTO;
import com.arch.raon.domain.summary.service.ArticleService;
import com.arch.raon.global.dto.ResponseDTO;
import com.arch.raon.global.util.enums.ArticleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/summaries")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @GetMapping("/{article-category}")
    public ResponseEntity<ResponseDTO> getArticle(
            // @AuthenticationPrincipal Long memberId
            @PathVariable("article-category") ArticleCategory articleCategory){

        ArticleResponseDTO articleResponseDTO = articleService.getRandomArticle(articleCategory);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("뉴스 기사")
                        .data(articleResponseDTO)
                        .build());
    }
}
