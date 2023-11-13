package com.arch.raon.domain.grammar.dto.query;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class GrammarRankQueryDTO {
    private Long memberId;
    private int score;

    public GrammarRankQueryDTO() {
        super();
    }

    @Builder
    public GrammarRankQueryDTO(Long memberId, double score) {
        this.memberId = memberId;
        this.score = (int) score;
    }

    public static GrammarRankQueryDTO convertToGrammarMyRankQueryDTO(ZSetOperations.TypedTuple typedTuple){
        GrammarRankQueryDTO grammarRankQueryDTO = new GrammarRankQueryDTO();
        grammarRankQueryDTO.memberId = Long.valueOf(typedTuple.getValue().toString());
        grammarRankQueryDTO.score = typedTuple.getScore().intValue();
        return grammarRankQueryDTO;
    }

}
