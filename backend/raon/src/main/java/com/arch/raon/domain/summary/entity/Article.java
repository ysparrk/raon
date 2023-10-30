package com.arch.raon.domain.summary.entity;


import com.arch.raon.global.util.enums.ArticleCategory;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="article")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private LocalDateTime datetime;
}
