package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.response.MemberResDto;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberServiceImpl {

    private final MemberRepository memberRepository;

    // 회원 조회
    public MemberResDto findOneMember(Long id){
        Member findMember = memberRepository.findById(id).get();
        return MemberResDto.of(findMember);
    }
}
