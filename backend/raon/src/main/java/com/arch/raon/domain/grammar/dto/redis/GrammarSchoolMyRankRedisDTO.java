package com.arch.raon.domain.grammar.dto.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class GrammarSchoolMyRankRedisDTO {
    private String nickname;
    private int rank;
    private int score;

    public GrammarSchoolMyRankRedisDTO() {
        super();
    }

    @Builder
    public GrammarSchoolMyRankRedisDTO(String nickname, int rank, int score) {
        this.nickname = nickname;
        this.rank = rank;
        this.score = score;
    }

    public static GrammarSchoolMyRankRedisDTO convertToGrammarSchoolMyRankRedisDTO(ZSetOperations.TypedTuple<String> typedTuple, int rank){
        String[] values = typedTuple.getValue().split(":");
        String nickname = values[1];
        int score = typedTuple.getScore().intValue();
        return new GrammarSchoolMyRankRedisDTO(nickname, rank, score);
    }
}
