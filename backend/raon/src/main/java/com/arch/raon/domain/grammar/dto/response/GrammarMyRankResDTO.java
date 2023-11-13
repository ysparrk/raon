package com.arch.raon.domain.grammar.dto.response;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import com.arch.raon.global.util.enums.RankState;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Getter
public class GrammarMyRankResDTO {
    private Long myRank;
    private Long myScore;
    private RankState rankState;
    private List<GrammarMyRankQueryDTO> rankList;

    public GrammarMyRankResDTO() {
        super();
    }

    @Builder
    public GrammarMyRankResDTO(Long myRank, Long myScore, RankState rankState, List<GrammarMyRankQueryDTO> rankList) {
        this.myRank = myRank;
        this.myScore = myScore;
        this.rankState = rankState;
        this.rankList = rankList;
    }

    @Override
    public String toString() {
        return "GrammarMyRankResDTO{" +
                "myRank=" + myRank +
                ", myScore=" + myScore +
                ", rankState=" + rankState +
                ", rankList=" + rankList +
                '}';
    }
}
