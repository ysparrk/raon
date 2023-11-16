package com.arch.raon.domain.dictionary.dto.request;

import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import lombok.Builder;
import lombok.Getter;

@Getter
public class DictionaryScoreReqDTO {
    private int score;		// 최대 100

    public DictionaryScoreReqDTO() {
        super();
    }

    @Builder
    public DictionaryScoreReqDTO(int score) {
        setScore(score);
    }


    public void setScore(int score) {
        if(score <= 0 || score > 100){
            throw new CustomException(ErrorCode.DICTIONARY_SCORE_UNAVAILABLE) {
                @Override
                public ErrorCode getErrorCode() {
                    return super.getErrorCode();
                }
            };
        }
        this.score = score;
    }

    @Override
    public String toString() {
        return "DictionaryScoreReqDto{" +
                "score=" + score +
                '}';
    }
}
