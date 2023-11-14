package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryMyRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionarySchoolMyRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionarySchoolRankResDTO;

public interface DictionaryService {
    DictionaryQuizResDTO getDictionaryQuizzes();
    void saveDictionaryQuizResult(Long memberId, DictionaryScoreReqDTO dictionaryScoreReqDTO);
    DictionaryMyRankResDTO getMyRank(Long memberId);
    DictionarySchoolMyRankResDTO getSchoolMyRanking(Long memberId);
    DictionarySchoolRankResDTO getSchoolRanking(Long memberId);

}
