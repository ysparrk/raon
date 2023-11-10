package com.arch.raon.domain.dictionary.dto.query;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.redis.core.ZSetOperations;

@Getter
public class DictionaryMyRankQueryDTO {
    private Long id;
    private int score;

    public DictionaryMyRankQueryDTO() {
    }

    @Builder
    public DictionaryMyRankQueryDTO(Long id, double score) {
        this.id = id;
        this.score = (int) score;
    }

    public static DictionaryMyRankQueryDTO convertToDictionaryMyRankQueryDTO(ZSetOperations.TypedTuple typedTuple){
        DictionaryMyRankQueryDTO dictionaryMyRankQueryDTO=new DictionaryMyRankQueryDTO();
        dictionaryMyRankQueryDTO.id= Long.valueOf(typedTuple.getValue().toString());
        dictionaryMyRankQueryDTO.score=typedTuple.getScore().intValue();
        return dictionaryMyRankQueryDTO;
    }
}
