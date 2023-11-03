package com.arch.raon.domain.grammar.dto.response;

import com.arch.raon.domain.grammar.dto.query.GrammarMyRankQueryDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class GrammarMyRankingResDTO {
    private int myRank;
    private int myScore;
    private Boolean myState;
    private List<GrammarMyRankQueryDTO> rankList;

    public GrammarMyRankingResDTO() {
        super();
    }

    @Override
    public String toString() {
        return "GrammarMyRankingResDTO{" +
                "myRank=" + myRank +
                ", myScore=" + myScore +
                ", myState=" + myState +
                ", rankList=" + rankList +
                '}';
    }
}
