package com.arch.raon.domain.dictionary.dto.query;

import lombok.Builder;
import lombok.Getter;

@Getter
public class DictionaryRankQueryDTO {
    private String nickname;
    private int score;

    public DictionaryRankQueryDTO() {
    }

    @Builder
    public DictionaryRankQueryDTO(String nickname, double score) {
        this.nickname = nickname;
        this.score = (int) score;
    }
}
