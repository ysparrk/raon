package com.arch.raon.global.auth.service;

import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.auth.enums.AuthProvider;
import com.arch.raon.global.auth.enums.RoleType;
import com.arch.raon.global.auth.oauth2.OAuth2UserInfo;
import com.arch.raon.global.auth.oauth2.OAuth2UserInfoFactory;
import com.arch.raon.global.auth.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
        OAuth2UserService oAuth2UserService = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = oAuth2UserService.loadUser(oAuth2UserRequest);

        return processOAuth2User(oAuth2UserRequest, oAuth2User);
    }

    protected OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        //OAuth2 로그인 플랫폼 구분
        AuthProvider authProvider = AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId().toUpperCase());
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(authProvider, oAuth2User.getAttributes());

        if (!StringUtils.hasText(oAuth2UserInfo.getEmail())) {
            throw new RuntimeException("Email not found from OAuth2 provider");
        }

        Member member = memberRepository.findByEmail(oAuth2UserInfo.getEmail()).orElse(null);
        //이미 가입된 경우
        if (member != null) {
            if (!member.getAuthProvider().equals(authProvider)) {
                throw new RuntimeException("Email already signed up.");
            }
            member = updateUser(member, oAuth2UserInfo);
        }
        //가입되지 않은 경우
        else {
            member = registerUser(authProvider, oAuth2UserInfo);
            System.out.println("가입되지 않은 경우");
        }

        System.out.println("커스텀 오어스 유저 서비스");
        return UserPrincipal.create(member, oAuth2UserInfo.getAttributes());
    }

    private Member registerUser(AuthProvider authProvider, OAuth2UserInfo oAuth2UserInfo) {
        System.out.println("레포에 저장");
        Member member = Member.builder()
                .email(oAuth2UserInfo.getEmail())
                .nickname(oAuth2UserInfo.getName())
                .profileUrl(oAuth2UserInfo.getImageUrl())
                .oauth2Id(oAuth2UserInfo.getOAuth2Id())
                .authProvider(authProvider)
                .roleType(RoleType.USER)
                .build();

        return memberRepository.save(member);
    }

    private Member updateUser(Member member, OAuth2UserInfo oAuth2UserInfo) {

        return memberRepository.save(member.update(oAuth2UserInfo));
    }
}

