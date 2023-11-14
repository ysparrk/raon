package com.arch.raon.domain.grammar.dto.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class GrammarMyRankRedisDTO {
    private String ranker;
    private int score;

    public GrammarMyRankRedisDTO() {
        super();
    }

    @Builder
    public GrammarMyRankRedisDTO(String ranker, int score) {
        this.ranker = ranker;
        this.score = score;
    }

    public static GrammarMyRankRedisDTO convertToGrammarMyRankRedisDTO(ZSetOperations.TypedTuple<String> typedTuple){
        String nickname = typedTuple.getValue();
        int score = typedTuple.getScore().intValue();
        return new GrammarMyRankRedisDTO(nickname, score);
    }

}
