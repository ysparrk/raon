package com.arch.raon.domain.grammar.dto.response;

import com.arch.raon.domain.grammar.dto.redis.GrammarMyRankRedisDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GrammarMyRankListResDTO {
    private Long myRank;
    private double myScore;
    private List<GrammarMyRankRedisDTO> rankList;

    public GrammarMyRankListResDTO() {
        super();
    }

    @Builder
    public GrammarMyRankListResDTO(Long myRank, double myScore, List<GrammarMyRankRedisDTO> rankList) {
        this.myRank = myRank;
        this.myScore = myScore;
        this.rankList = rankList;
    }

    @Override
    public String toString() {
        return "GrammarMyRankListResDTO{" +
                "myRank=" + myRank +
                ", myScore=" + myScore +
                ", rankList=" + rankList +
                '}';
    }
}
