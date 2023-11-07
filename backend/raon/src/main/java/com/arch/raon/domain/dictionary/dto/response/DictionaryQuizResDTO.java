package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionaryQuizResDTO {
    private List<DictionaryInitialQuiz> initialQuizList;
    private List<DictionaryDirectionQuiz> directionQuizList;

    private String message;

    public DictionaryQuizResDTO(){
        super();
    }

    @Builder
    public DictionaryQuizResDTO(List<DictionaryInitialQuiz> initialQuizList, List<DictionaryDirectionQuiz> directionQuizList, String message) {
        this.initialQuizList = initialQuizList;
        this.directionQuizList = directionQuizList;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DictionaryQuizResponseDto{" +
                "initialQuizList=" + initialQuizList +
                ", directionQuizList=" + directionQuizList +
                '}';
    }
}
