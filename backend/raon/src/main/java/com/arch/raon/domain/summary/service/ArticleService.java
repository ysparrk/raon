package com.arch.raon.domain.summary.service;

import com.arch.raon.domain.summary.dto.response.ArticleResponseDTO;
import com.arch.raon.global.util.enums.ArticleCategory;


public interface ArticleService {
    ArticleResponseDTO getRandomArticle(ArticleCategory articleCategory);
}
