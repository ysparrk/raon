package com.arch.raon.domain.grammar.dto.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class GrammarMyRankRedisDTO {
    private String nickname;
    private int rank;
    private int score;

    public GrammarMyRankRedisDTO() {
        super();
    }

    @Builder
    public GrammarMyRankRedisDTO(String nickname, int rank, int score) {
        this.nickname = nickname;
        this.rank = rank;
        this.score = score;
    }

    public static GrammarMyRankRedisDTO convertToGrammarMyRankRedisDTO(ZSetOperations.TypedTuple<String> typedTuple, int rank){
        String nickname = typedTuple.getValue();
        int score = typedTuple.getScore().intValue();
        return new GrammarMyRankRedisDTO(nickname, rank, score);
    }

}
