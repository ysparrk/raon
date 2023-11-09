package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionaryMyRankResDTO {
    private long myRank;
    private int myScore;
    private List<DictionaryMyRankQueryDTO> myRankList;
    private List<DictionaryMyRankQueryDTO> topRankList;

    @Builder
    public DictionaryMyRankResDTO(long myRank, double myScore, List<DictionaryMyRankQueryDTO> myRankList, List<DictionaryMyRankQueryDTO> topRankList) {
        this.myRank = myRank;
        this.myScore = (int) myScore;
        this.myRankList = myRankList;
        this.topRankList = topRankList;
    }

    public DictionaryMyRankResDTO() {
    }
}
