package com.arch.raon.domain.dictionary.dto.response;

import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.global.util.enums.SocketResponse;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class DictionaryQuizResDTO {
    private List<DictionaryInitialQuiz> initialQuizList;
    private List<DictionaryDirectionQuiz> directionQuizList;

    private SocketResponse message;

    public DictionaryQuizResDTO(){
        super();
    }

    @Builder
    public DictionaryQuizResDTO(List<DictionaryInitialQuiz> initialQuizList, List<DictionaryDirectionQuiz> directionQuizList, SocketResponse message) {
        this.initialQuizList = initialQuizList;
        this.directionQuizList = directionQuizList;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DictionaryQuizResDTO{" +
            "initialQuizList=" + initialQuizList +
            ", directionQuizList=" + directionQuizList +
            ", message=" + message +
            '}';
    }
}
