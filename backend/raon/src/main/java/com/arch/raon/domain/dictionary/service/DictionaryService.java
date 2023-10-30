package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDto;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDto;

public interface DictionaryService {
    DictionaryQuizResDto getDictionaryQuizzes();
    void saveDictionaryQuizResult(DictionaryScoreReqDto dictionaryScoreReqDto);

}
