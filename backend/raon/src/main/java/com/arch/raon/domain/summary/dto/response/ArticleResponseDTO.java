package com.arch.raon.domain.summary.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ArticleResponseDTO {
    private String title;
    private String content;
    private LocalDateTime datetime;

    public ArticleResponseDTO() { super(); }

    @Builder
    public ArticleResponseDTO(String title, String content, LocalDateTime datetime) {
        this.title = title;
        this.content = content;
        this.datetime = datetime;
    }

    @Override
    public String toString() {
        return "SummaryQuizResponseDto{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", datetime=" + datetime +
                '}';
    }
}
