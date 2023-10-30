package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;

public interface DictionaryService {
    DictionaryQuizResDTO getDictionaryQuizzes();
    void saveDictionaryQuizResult(DictionaryScoreReqDTO dictionaryScoreReqDTO);

}
