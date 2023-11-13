package com.arch.raon.domain.member.controller;

import com.arch.raon.domain.member.dto.request.MemberCheckNicknameReqDTO;
import com.arch.raon.domain.member.dto.request.MemberCheckSchoolReqDTO;
import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.domain.member.dto.response.CheckActiveResDTO;
import com.arch.raon.domain.member.dto.response.MemberCheckSchoolResDTO;
import com.arch.raon.domain.member.dto.response.MemberDetailResDTO;
import com.arch.raon.domain.member.service.MemberService;
import com.arch.raon.global.auth.dto.UserAuthentication;
import com.arch.raon.global.dto.ResponseDTO;
import com.arch.raon.global.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final RedisService redisService;

    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(
            @AuthenticationPrincipal UserAuthentication userAuth,
            @RequestBody MemberSignupReqDTO memberSignupReqDTO) throws Exception {
        memberService.signup(userAuth.getId(), memberSignupReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("회원가입 성공")
                        .build());
    }

    @GetMapping("/active")
    public ResponseEntity<ResponseDTO> checkActive(
            @AuthenticationPrincipal UserAuthentication userAuth) {
        CheckActiveResDTO checkActiveResDTO = memberService.checkActive(userAuth.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("추가 정보 입력 여부 확인 성공")
                        .data(checkActiveResDTO)
                        .build());
    }

    @PostMapping("/test/set")
    public String testSet(){
        redisService.setTestData("arch");
        return "set";
    }

    @PostMapping("/test/get")
    public String testGet(@AuthenticationPrincipal UserAuthentication userAuth){
        String str = redisService.getTestData("arch");
        System.out.println(userAuth.getId());
        return str;
    }

    @PostMapping("/check/nickname")
    public ResponseEntity<ResponseDTO> checkNickname(
            @AuthenticationPrincipal UserAuthentication userAuth,
            @RequestBody MemberCheckNicknameReqDTO memberCheckNicknameReqDTO) {
        boolean isAble = memberService.checkNickname(memberCheckNicknameReqDTO.getNickname());
        if(!isAble){
            return ResponseEntity.status(HttpStatus.OK)
                    .body(ResponseDTO.builder()
                            .message("중복된 닉네임 입니다.")
                            .build());
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("사용 가능한 닉네임 입니다.")
                        .build());
    }

    @PostMapping("/modify")
    public ResponseEntity<ResponseDTO> modifyMember(
            @AuthenticationPrincipal UserAuthentication userAuth,
            @RequestBody MemberSignupReqDTO memberSignupReqDTO) {

        memberService.modifyMember(userAuth.getId(),memberSignupReqDTO);


        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("회원정보 수정 완료")
                        .build());
    }

    @PostMapping("/detail")
    public ResponseEntity<ResponseDTO> detailMember(
            @AuthenticationPrincipal UserAuthentication userAuth) {

        MemberDetailResDTO memberDetailResDTO = memberService.detailMember(userAuth.getId());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("회원정보 조회")
                        .data(memberDetailResDTO)
                        .build());
    }

    @PostMapping("/check-school")
    public ResponseEntity<ResponseDTO> checkSchool(
            @RequestBody MemberCheckSchoolReqDTO memberCheckSchoolReqDTO
            ) {

        MemberCheckSchoolResDTO memberCheckSchoolResDTO = memberService.checkSchool(memberCheckSchoolReqDTO.getKeyword());

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("학교 명 조회")
                        .data(memberCheckSchoolResDTO)
                        .build());
    }

}
