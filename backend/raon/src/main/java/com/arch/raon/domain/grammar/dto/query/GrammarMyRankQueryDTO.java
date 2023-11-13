package com.arch.raon.domain.grammar.dto.query;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Getter;

@Getter
public class GrammarMyRankQueryDTO {
    private Long rank;
    private String nickname;
    private int score;

    public GrammarMyRankQueryDTO() {
        super();
    }

    @QueryProjection
    public GrammarMyRankQueryDTO(String nickname, int score) {
        this.nickname = nickname;
        this.score = score;
    }

    @Builder
    public GrammarMyRankQueryDTO(Long rank, String nickname, int score) {
        this.rank = rank;
        this.nickname = nickname;
        this.score = score;
    }

    @Override
    public String toString() {
        return "GrammarMyRankQueryDTO{" +
                "rank=" + rank +
                ", nickname='" + nickname + '\'' +
                ", score=" + score +
                '}';
    }
}
