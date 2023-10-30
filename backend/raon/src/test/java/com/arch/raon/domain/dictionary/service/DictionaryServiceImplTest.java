package com.arch.raon.domain.dictionary.service;

import com.arch.raon.domain.dictionary.dto.response.DictionaryQuizResponseDto;
import com.arch.raon.domain.dictionary.repository.DictionaryDirectionQuizRepository;
import com.arch.raon.domain.dictionary.repository.DictionaryInitialQuizRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class DictionaryServiceImplTest {

    @Autowired
    DictionaryInitialQuizRepository dictionaryInitialQuizRepository;
    @Autowired
    DictionaryDirectionQuizRepository dictionaryDirectionQuizRepository;
    @Autowired
    DictionaryService dictionaryService;

    @DisplayName("국어사전 퀴즈 랜덤 문제")
    @Test
    void getDictionaryQuizzes() {
        // given

        // when
        DictionaryQuizResponseDto expectList = dictionaryService.getDictionaryQuizzes();

        // then
        assertThat(7).isEqualTo(expectList.getInitialQuizList().size());
        assertThat(3).isEqualTo(expectList.getDirectionQuizList().size());
    }
}