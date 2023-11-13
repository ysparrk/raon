package com.arch.raon.global.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;


@Service
public class RedisService {

    private final RedisTemplate<String, String> rankingRedis;
    private final RedisTemplate<String, String> securityRedis;

    @Autowired
    public RedisService(@Qualifier("RankingRedis") RedisTemplate<String, String> rankingRedis,
                        @Qualifier("SecurityRedis") RedisTemplate<String, String> securityRedis) {
        this.rankingRedis = rankingRedis;
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
    public void setRefreshToken(String accessToken, String refreshToken){
        // key : accessToken, value : refreshToken
        securityRedis.opsForValue().set(accessToken, refreshToken);
        //30일
        securityRedis.expire(accessToken,30L, TimeUnit.DAYS);
    }

    public String getRefreshToken(String accessToken){
        return securityRedis.opsForValue().get(accessToken);
    }


    public boolean deleteRefreshToken(String accessToken){
        return Boolean.TRUE.equals(securityRedis.delete(accessToken));
    }

    public void updateRefreshTokenKey(String accessToken, String newAccessToken){
        securityRedis.rename(accessToken, newAccessToken);
    }

}
