package com.arch.raon.domain.grammar.dto.response;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.global.util.enums.RankState;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GrammarMyRankingResDTO {
    private int myRank;
    private int myScore;
    private RankState rankState;
    private List<GrammarMyRankQueryDTO> rankList;

    public GrammarMyRankingResDTO() {
        super();
    }

    @Builder
    public GrammarMyRankingResDTO(int myRank, int myScore, RankState rankState, List<GrammarMyRankQueryDTO> rankList) {
        this.myRank = myRank;
        this.myScore = myScore;
        this.rankState = rankState;
        this.rankList = rankList;
    }

    @Override
    public String toString() {
        return "GrammarMyRankingResDTO{" +
                "myRank=" + myRank +
                ", myScore=" + myScore +
                ", rankState=" + rankState +
                ", rankList=" + rankList +
                '}';
    }
}
