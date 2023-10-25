package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.response.MemberResDto;

public interface MemberService {
    MemberResDto findOneMember(Long id);
}
