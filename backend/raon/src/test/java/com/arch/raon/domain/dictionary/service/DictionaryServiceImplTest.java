package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResDTO;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryScoreRepository;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.util.enums.Gender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DictionaryServiceImplTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
    @Autowired
    DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
    @Autowired
    DictionaryScoreRepository dictionaryScoreRepository;
    @Autowired
    DictionaryService dictionaryService;

    static Member MEMBER1;

    @BeforeEach
    void beforeEach() {
        MEMBER1 = Member.builder()
                .id(6L)
                .email("arch@arch.com")
                .nickname("아치아빠재우")
                .profileUrl("https://")
                .gender(Gender.MALE)
                .school("ARCH")
                .yearOfBirth(2017)
                .mileage(0)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .isDeleted(false)
                .deletedAt(LocalDateTime.now())
                .build();

        memberRepository.save(MEMBER1);
    }


    @DisplayName("국어사전 퀴즈 랜덤 문제")
    @Test
    @Transactional
    void getDictionaryQuizzes() {
        // given

        // when
        DictionaryQuizResDTO expectList = dictionaryService.getDictionaryQuizzes();

        // then
        assertThat(7).isEqualTo(expectList.getInitialQuizList().size());
        assertThat(3).isEqualTo(expectList.getDirectionQuizList().size());
    }


//    @DisplayName("국어사전 퀴즈 점수 저장")
//    @Test
//    @Transactional
//    void saveDictionaryQuizResult() {
//        // given
//        DictionaryScoreReqDTO dictionaryScoreReqDTO = DictionaryScoreReqDTO.builder()
//                .score(70)
//                .build();
//
//        // when
//        dictionaryService.saveDictionaryQuizResult(MEMBER1.getId(), dictionaryScoreReqDTO);
//
//        // then
//        assertThat(70).isEqualTo(dictionaryScoreRepository.findByMemberId(MEMBER1.getId()).get().getScore());
//    }
}