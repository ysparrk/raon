package com.arch.raon.domain.grammar.dto.query;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class GrammarMyRankQueryDTO {
    private int rank;
    private String nickname;
    private int score;

    public GrammarMyRankQueryDTO() {
        super();
    }

    @QueryProjection
    public GrammarMyRankQueryDTO(int rank, String nickname, int score) {
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
