package com.arch.raon.domain.member.controller;

import com.arch.raon.global.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

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

}
