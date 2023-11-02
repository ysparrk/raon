package com.arch.raon.domain.member.service;

import com.arch.raon.global.auth.dto.AuthUserInfo;
import com.arch.raon.global.auth.dto.OAuthUserInfo;

public interface MemberService {
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo);
}
