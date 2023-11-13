package com.arch.raon.domain.grammar.dto.redis;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class GrammarSchoolRedisDTO {
    private String school;
    private int rank;
    private int score;

    public GrammarSchoolRedisDTO() {
        super();
    }

    @Builder
    public GrammarSchoolRedisDTO(String school, int rank, int score) {
        this.school = school;
        this.rank = rank;
        this.score = score;
    }

    public static GrammarSchoolRedisDTO convertToGrammarSchoolRedisDTO(ZSetOperations.TypedTuple<String> typedTuple, int rank){
        GrammarSchoolRedisDTO grammarSchoolRedisDTO = new GrammarSchoolRedisDTO();
        grammarSchoolRedisDTO.school = typedTuple.getValue();
        grammarSchoolRedisDTO.rank = rank;
        grammarSchoolRedisDTO.score = typedTuple.getScore().intValue();
        return grammarSchoolRedisDTO;
    }
}
