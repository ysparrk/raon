package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.domain.member.dto.response.CheckActiveResDTO;
import com.arch.raon.global.auth.dto.AuthUserInfo;
import com.arch.raon.global.auth.dto.OAuthUserInfo;

public interface MemberService {
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo);
    public void signup(Long id, MemberSignupReqDTO memberSignupReqDTO);
    public CheckActiveResDTO checkActive(Long id);
    boolean checkNickname(String nickname);
}
