package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.query.DictionaryMyRankQueryDTO;
import com.arch.raon.domain.dictionary.dto.request.DictionaryScoreReqDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryMyRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionarySchoolMyRankResDTO;
import com.arch.raon.domain.dictionary.dto.response.DictionarySchoolRankResDTO;
import com.arch.raon.domain.dictionary.entity.DictionaryDirectionQuiz;
import com.arch.raon.domain.dictionary.entity.DictionaryInitialQuiz;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
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

        redisService.setCountryDictionaryPoint(member.getNickname(),dictionaryScoreReqDTO.getScore());
        redisService.setSchoolDictionaryPoint(member.getNickname(),member.getSchool(),dictionaryScoreReqDTO.getScore());
        redisService.setMySchoolDictionaryPoint(member.getSchool(),dictionaryScoreReqDTO.getScore());
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
        double myScore = redisService.getCountryDictionaryPoint(member.getNickname());
        long myRank = redisService.getCountryDictionaryMyRank(member.getNickname());
        myRank++;

        List<DictionaryMyRankQueryDTO> rankList = redisService.getCountryDictionaryRank();

        DictionaryMyRankResDTO dictionaryMyRankResDTO = DictionaryMyRankResDTO.builder()
                .myRank(myRank)
                .myScore(myScore)
                .rankList(rankList)
                .build();

        return dictionaryMyRankResDTO;
    }

    @Override
    public DictionarySchoolMyRankResDTO getSchoolMyRanking(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        if(member==null){
            throw new CustomException(ErrorCode.NO_SUCH_MEMBER) {
                @Override
                public ErrorCode getErrorCode() {
                    return super.getErrorCode();
                }
            };
        }
        double myScore = redisService.getSchoolMyDictionaryPoint(member.getNickname(),member.getSchool());
        long myRank = redisService.getSchoolMyDictionaryMyRank(member.getNickname(), member.getSchool());
        myRank++;

        List<DictionaryMyRankQueryDTO> rankList = redisService.getSchoolMyDictionaryRank(member.getSchool());

        DictionarySchoolMyRankResDTO dictionaryMyRankResDTO = DictionarySchoolMyRankResDTO.builder()
                .myRank(myRank)
                .myScore(myScore)
                .rankList(rankList)
                .build();

        return dictionaryMyRankResDTO;
    }

    @Override
    public DictionarySchoolRankResDTO getSchoolRanking(Long memberId){
        Member member = memberRepository.findById(memberId).get();
        if(member==null){
            throw new CustomException(ErrorCode.NO_SUCH_MEMBER) {
                @Override
                public ErrorCode getErrorCode() {
                    return super.getErrorCode();
                }
            };
        }
        double myScore = redisService.getSchoolDictionaryPoint(member.getSchool());
        long myRank = redisService.getSchoolDictionaryMyRank(member.getSchool());
        myRank++;

        List<DictionaryMyRankQueryDTO> rankList = redisService.getSchoolDictionaryRank(member.getSchool());

        DictionarySchoolRankResDTO dictionarySchoolRankResDTO = DictionarySchoolRankResDTO.builder()
                .myRank(myRank)
                .myScore(myScore)
                .rankList(rankList)
                .build();

        return dictionarySchoolRankResDTO;
    }

}
