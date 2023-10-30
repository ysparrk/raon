package com.arch.raon.domain.summary.repository;

import com.arch.raon.domain.summary.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ArticleRepository extends JpaRepository<Article, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM article WHERE category = :category AND ORDER BY RAND() LIMIT 1")
    Article getRandomArticleWithMinimumLength(@Param("category") String category);
}
