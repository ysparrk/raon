package com.arch.raon.domain.summary.service;

import com.arch.raon.domain.summary.dto.response.ArticleResponseDTO;
import com.arch.raon.domain.summary.entity.Article;
import com.arch.raon.domain.summary.repository.ArticleRepository;
import com.arch.raon.global.util.enums.ArticleCategory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;

    @Override
    public ArticleResponseDTO getRandomArticle(ArticleCategory articleCategory) {
        Article randomArticle = articleRepository.getRandomArticleWithMinimumLength(articleCategory.name());

        ArticleResponseDTO articleResponseDTO = ArticleResponseDTO.builder()
                .title(randomArticle.getTitle())
                .content(randomArticle.getContent())
                .datetime(randomArticle.getDatetime()).build();

        return articleResponseDTO;
    }
}
