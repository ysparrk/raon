package com.arch.raon.domain.grammar.dto.response;

import com.arch.raon.domain.grammar.dto.redis.GrammarSchoolRedisDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GrammarSchoolRankListResDTO {
    private Long mySchoolRank;
    private double mySchoolScore;
    private List<GrammarSchoolRedisDTO> rankList;

    public GrammarSchoolRankListResDTO() {
        super();
    }

    @Builder
    public GrammarSchoolRankListResDTO(Long mySchoolRank, double mySchoolScore, List<GrammarSchoolRedisDTO> rankList) {
        this.mySchoolRank = mySchoolRank;
        this.mySchoolScore = mySchoolScore;
        this.rankList = rankList;
    }

    @Override
    public String toString() {
        return "GrammarSchoolRankListResDTO{" +
                "mySchoolRank=" + mySchoolRank +
                ", mySchoolScore=" + mySchoolScore +
                ", rankList=" + rankList +
                '}';
    }
}
