package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
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
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    static Member MEMBER1;

    @BeforeEach
    void beforeEach() {
        MEMBER1 = Member.builder()
                .email("arch@arch.com")
                .nickname("테스트1")
                .profileUrl("https://")
                .mileage(0)
                .createdAt(LocalDateTime.now())
                .modifiedAt(LocalDateTime.now())
                .isDeleted(false)
                .deletedAt(LocalDateTime.now())
                .build();

        memberRepository.save(MEMBER1);
    }

    @DisplayName("회원정보 입력")
    @Test
    @Transactional
    void signup() {
        // given
        MemberSignupReqDTO memberSignupReqDTO = MemberSignupReqDTO.builder()
                .nickname("테스트1")
                .school("ARCH")
                .gender(Gender.MALE)
                .yearOfBirth(2020).build();

        // when
        memberService.signup(MEMBER1.getId(), memberSignupReqDTO);

        // then
        assertThat("테스트1").isEqualTo(memberRepository.findById(MEMBER1.getId()).get().getNickname());
    }
}