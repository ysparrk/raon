package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionarySchoolMyRankResDTO {
    private long myRank;
    private int myScore;
    private List<DictionaryMyRankQueryDTO> rankList;


    @Builder
    public DictionarySchoolMyRankResDTO(long myRank, double myScore, List<DictionaryMyRankQueryDTO> rankList) {
        this.myRank = myRank;
        this.myScore = (int) myScore;
        this.rankList = rankList;
    }

    public DictionarySchoolMyRankResDTO() {
    }
}
