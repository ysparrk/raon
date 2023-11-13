package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.dictionary.dto.query.DictionaryRankQueryDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionaryMyRankResDTO {
    private long myRank;
    private int myScore;
    private List<DictionaryRankQueryDTO> myRankList;
    private List<DictionaryRankQueryDTO> topRankList;

    @Builder
    public DictionaryMyRankResDTO(long myRank, double myScore, List<DictionaryRankQueryDTO> myRankList, List<DictionaryRankQueryDTO> topRankList) {
        this.myRank = myRank;
        this.myScore = (int) myScore;
        this.myRankList = myRankList;
        this.topRankList = topRankList;
    }

    public DictionaryMyRankResDTO() {
    }
}
