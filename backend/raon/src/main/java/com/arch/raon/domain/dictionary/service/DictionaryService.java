package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;

public interface DictionaryService {
    DictionaryQuizResDTO getDictionaryQuizzes();
    void saveDictionaryQuizResult(Long memberId, DictionaryScoreReqDTO dictionaryScoreReqDTO);
    DictionaryRankResDTO getMyRank(Long memberId);
    DictionaryRankResDTO getSchoolMyRanking(Long memberId);
    DictionaryRankResDTO getSchoolRanking(Long memberId);

}
