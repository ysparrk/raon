package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.dictionary.dto.query.DictionaryRankQueryDTO;
import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryMyRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryScoreRepository;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DictionaryServiceImpl implements DictionaryService {

    private final MemberRepository memberRepository;
    private final DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
    private final DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
    private final RedisService redisService;

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
    public void saveDictionaryQuizResult(Long memberId, DictionaryScoreReqDTO dictionaryScoreReqDTO) {

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
            @Override
            public ErrorCode getErrorCode() {
                return super.getErrorCode();
            }
        });

        redisService.setCountryDictionaryPoint(memberId,dictionaryScoreReqDTO.getScore());
    }

    @Override
    public DictionaryMyRankResDTO getMyRank(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        if(member==null){
            throw new CustomException(ErrorCode.NO_SUCH_MEMBER) {
                @Override
                public ErrorCode getErrorCode() {
                    return super.getErrorCode();
                }
            };
        }
        double myScore = redisService.getCountryDictionaryPoint(memberId);
        long myRank = redisService.getCountryDictionaryMyRank(memberId);
        myRank++;

        List<DictionaryMyRankQueryDTO> myRankIdList = redisService.getCountryDictionaryRank(myRank);
        List<DictionaryMyRankQueryDTO> topRankIdList = redisService.getCountryDictionaryRank(0);

        List<DictionaryRankQueryDTO> myRankList = new ArrayList<>();
        List<DictionaryRankQueryDTO> topRankList = new ArrayList<>();

        for(DictionaryMyRankQueryDTO d : myRankIdList){
            member = memberRepository.findById(d.getId()).get();
            if(member==null){
                continue;
            }
            myRankList.add(new DictionaryRankQueryDTO(member.getNickname(),d.getScore()));
        }

        for(DictionaryMyRankQueryDTO d : topRankIdList){
            member = memberRepository.findById(d.getId()).get();
            if(member==null){
                continue;
            }
            topRankList.add(new DictionaryRankQueryDTO(member.getNickname(),d.getScore()));
        }

        DictionaryMyRankResDTO dictionaryMyRankResDTO = DictionaryMyRankResDTO.builder()
                .myRank(myRank)
                .myScore(myScore)
                .myRankList(myRankList)
                .topRankList(topRankList)
                .build();

        return dictionaryMyRankResDTO;
    }

}
