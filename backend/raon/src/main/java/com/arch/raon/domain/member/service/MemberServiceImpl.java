package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.SignupReqDto;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member findOneMember(Long id){
        return memberRepository.findById(id).get();
    }

    @Transactional
    @Override
    public Long signup(SignupReqDto signupReqDto) {
        Member member = Member.builder()
                .email(signupReqDto.getEmail())
                .nickname(signupReqDto.getNickname())
                .profileUrl(signupReqDto.getProfileUrl())
                .school(signupReqDto.getSchool())
                .gender(signupReqDto.getGender())
                .yearOfBirth(signupReqDto.getYearOfBirth()).build();
        memberRepository.save(member);
        return member.getId();
    }
}
