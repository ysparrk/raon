package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.util.enums.Gender;
import com.arch.raon.global.util.enums.School;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @Transactional
    void signup() {
        // given
        Long testMemberId = 7420L;
        MemberSignupReqDTO memberSignupReqDTO = MemberSignupReqDTO.builder()
                .nickname("새로운닉네임")
                .school(School.ARCH)
                .gender(Gender.MALE)
                .yearOfBirth(2020).build();

        // when
        memberService.signup(testMemberId, memberSignupReqDTO);

        // then
        assertThat("새로운닉네임").isEqualTo(memberRepository.findById(testMemberId).get().getNickname());
    }
}