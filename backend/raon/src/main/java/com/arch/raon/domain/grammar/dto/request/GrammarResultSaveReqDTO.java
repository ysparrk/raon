package com.arch.raon.domain.grammar.dto.request;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class GrammarResultSaveReqDTO {
    private List<GrammarResultDTO> grammarResultList;

    public GrammarResultSaveReqDTO() {
        super();
    }

    @Builder
    public GrammarResultSaveReqDTO(List<GrammarResultDTO> grammarResultList) {
        this.grammarResultList = grammarResultList;
    }

    @Override
    public String toString() {
        return "GrammarResultSaveReqDTO{" +
                "grammarResultList=" + grammarResultList +
                '}';
    }
}
