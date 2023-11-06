package com.arch.raon.global.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    private final RedisTemplate<String, Object> quizRedis;
    private final RedisTemplate<String, String> securityRedis;

    @Autowired
    public RedisService(@Qualifier("QuizRedis") RedisTemplate<String, Object> quizRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis) {
        this.quizRedis = quizRedis;
        this.securityRedis = securityRedis;
    }

    public void setTestData(String str){
        securityRedis.opsForValue().set(str,"please");
        securityRedis.expire(str,30, TimeUnit.SECONDS);
    }

    public String getTestData(String str){
        return securityRedis.opsForValue().get(str);
    }

    /** 여기에 리프레쉬 관련 로직 넣으면 좋아용 **/
    /*
    public void setRefreshToken(String userId, String refreshToken){
        // key : refresh, value : userId
        securityRedis.opsForValue().set(userId,refreshToken);
        //30일
        securityRedis.expire(userId,30L, TimeUnit.DAYS);
    }

    public String getRefreshToken(String userId){
        return securityRedis.opsForValue().get(userId);
    }

    public boolean deleteRefreshToken(String userId){
        return securityRedis.delete(userId);
    }

     */

}
