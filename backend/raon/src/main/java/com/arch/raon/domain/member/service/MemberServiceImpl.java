package com.arch.raon.domain.member.service;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.domain.member.dto.response.CheckActiveResDTO;
import com.arch.raon.domain.member.dto.response.MemberDetailResDTO;
import com.arch.raon.domain.member.entity.Member;
import com.arch.raon.domain.member.repository.MemberRepository;
import com.arch.raon.global.auth.dto.AuthUserInfo;
import com.arch.raon.global.auth.dto.OAuthUserInfo;
import com.arch.raon.global.exception.CustomException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.util.enums.Gender;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public AuthUserInfo getOrRegisterUser(OAuthUserInfo oauthUserInfo) {

        // 유저가 존재하는지 확인
        Member member = memberRepository.findByEmail(oauthUserInfo.getEmail());

        // 일단 등록은 해놓고 추가 정보 없을 시 입력하는 페이지로 이동하도록 처리
        if(member == null){
            member = Member.builder()
                    .nickname(oauthUserInfo.getNickname())
                    .email(oauthUserInfo.getEmail())
                    .profileUrl(oauthUserInfo.getProfileUrl())
                    .school("not Select")
                    .yearOfBirth(0)
                    .mileage(0)
                    .isDeleted(false)
                    .isActive(Boolean.FALSE)
                    .build();
            System.out.println("oauthUserInfo.getProfileUrl() = " + oauthUserInfo.getProfileUrl());
            memberRepository.save(member);
        }
        return new AuthUserInfo(member.getId(), member.getEmail(), Arrays.asList("USER"));
    }

    @Override
    @Transactional
    public void signup(Long id, MemberSignupReqDTO memberSignupReqDTO) {
        Member member = memberRepository.findById(id).orElseThrow();
        member.signup(memberSignupReqDTO);
    }

    @Override
    public CheckActiveResDTO checkActive(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();

        return CheckActiveResDTO.builder()
                .active(member.getIsActive())
                .nickname(member.getNickname())
                .build();
    }
    @Override
    public boolean checkNickname(String nickname){
        Optional<Member> byNickname = memberRepository.findByNickname(nickname);
        if(byNickname.isPresent()){
            return false;
        }
        return true;
    }

    @Override
    @Transactional
    public void modifyMember(Long id, MemberSignupReqDTO memberSignupReqDTO){
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
            @Override
            public ErrorCode getErrorCode() {
                return super.getErrorCode();
            }
        });

        member.signup(memberSignupReqDTO);
    }

    @Override
    public MemberDetailResDTO detailMember(Long id){
        Member member = memberRepository.findById(id).orElseThrow(() -> new CustomException(ErrorCode.MEMBER_NOT_FOUND) {
            @Override
            public ErrorCode getErrorCode() {
                return super.getErrorCode();
            }
        });

        return MemberDetailResDTO.builder()
                .profileUrl(member.getProfileUrl())
                .nickname(member.getNickname())
                .school(member.getSchool())
                .yearOfBirth(member.getYearOfBirth())
                .gender(member.getGender())
                .build();
    }
}
