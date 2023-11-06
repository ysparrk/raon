package com.arch.raon.domain.member.controller;

import com.arch.raon.domain.member.dto.request.MemberSignupReqDTO;
import com.arch.raon.domain.member.service.MemberService;
import com.arch.raon.global.auth.dto.CustomOAuth2User;
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

    @PostMapping("/test/set")
    public String testSet(){
        redisService.setTestData("arch");
        return "set";
    }

    @PostMapping("/test/get")
    public String testGet(){
        String str = redisService.getTestData("arch");
        return str;
    }



    @PostMapping("/signup")
    public ResponseEntity<ResponseDTO> signup(
            @AuthenticationPrincipal CustomOAuth2User oAuth2User,
            @RequestBody MemberSignupReqDTO memberSignupReqDTO) throws Exception {
        memberService.signup(oAuth2User.getId(), memberSignupReqDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(ResponseDTO.builder()
                        .message("회원가입 성공")
                        .build());
    }

}
