package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.SignupReqDto;
import com.arch.raon.domain.member.entity.Member;

public interface MemberService {
    Member findOneMember(Long id);
    Long signup(SignupReqDto signupReqDto);
}
