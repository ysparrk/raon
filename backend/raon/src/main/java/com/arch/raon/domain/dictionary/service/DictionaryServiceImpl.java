package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryScore;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryScoreRepository;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final MemberRepository memberRepository;
    private final DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
    private final DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
    private final DictionaryScoreRepository dictionaryScoreRepository;

    @Override
    public DictionaryQuizResDTO getDictionaryQuizzes() {

        List<DictionaryInitialQuiz> initialQuizList = dictionaryInitialQuizRepository.random7();
        List<DictionaryDirectionQuiz> directionQuizList = dictionaryDirectionQuizRepository.random3();

        DictionaryQuizResDTO quizList = DictionaryQuizResDTO.builder()
                .initialQuizList(initialQuizList)
                .directionQuizList(directionQuizList)
                .build();

        return quizList;
    }

    @Override
    @Transactional
    public void saveDictionaryQuizResult(DictionaryScoreReqDTO dictionaryScoreReqDTO) {
        // TODO: security 적용 후 member에 대한 검증
        final long TEST_MEMBER_ID = 6;

        Member member = memberRepository.findById(TEST_MEMBER_ID).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
            @Override
            public ErrorCode getErrorCode() {
                return super.getErrorCode();
            }
        });

        DictionaryScore dictionaryScore = DictionaryScore.builder()
                .member(member)
                .score(dictionaryScoreReqDTO.getScore())
                .build();

        dictionaryScoreRepository.save(dictionaryScore);
    }
}
