package com.arch.raon.domain.summary.entity;


import com.arch.raon.global.util.enums.ArticleCategory;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity(name="article")
@Getter
@Builder
public class Article {

    @Id
    @Column(name="id")
    private Long id;

    @Column(name="title",length = 128)
    private String title;

    @Column(name="content",length = 1024)
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(name="category")
    private ArticleCategory category;

    @Column(name = "date")
    private LocalDateTime date;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "modified_at", nullable = false)
    private LocalDateTime modifiedAt;

    public Article() {

    }

    public Article(Long id, String title, String content, ArticleCategory category, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.category = category;
        this.date = date;
    }

}
