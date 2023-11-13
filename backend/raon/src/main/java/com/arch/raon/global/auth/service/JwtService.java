package com.arch.raon.global.auth.service;

import com.arch.raon.global.auth.dto.ReissueTokenReqDTO;
import com.arch.raon.global.auth.exception.TokenException;
import com.arch.raon.global.exception.ErrorCode;
import com.arch.raon.global.service.RedisService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.Objects;


@Service
public class JwtService {
    private final RedisService redisService;
    private static final long MILLI_SECOND = 1000L;
    private final String secretKey;

    private final long expirationHours = 60 * 30;
    private final String issuer;


    //30일
    private final long refreshTokenExpire = 60 * 60 * 24 * 30;

    public JwtService(
            RedisService redisService,
            @Value("${custom.jwt.issuer}") String issuer,
            @Value("${custom.jwt.secretKey}") String secretKey
    ) {
        this.redisService = redisService;
        this.issuer=issuer;
        this.secretKey=secretKey;
    }

    public String createAccessToken(Long userSpecification){
        Date now = new Date();
        Date validity = new Date(now.getTime() + expirationHours * MILLI_SECOND);
        System.out.println("secretKey = " + secretKey);
        return Jwts.builder()
                .signWith((new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))) //HS512 알고리즘 이용, secretKey 이용
                .setSubject(String.valueOf(userSpecification)) //토큰에 담을 정보
                .setIssuer(issuer) //발급자
                .setIssuedAt(now) //발급시간
                .setExpiration(validity) //만료시간
                .compact();
    }

    public String createRefreshToken(){
        Date now = new Date();
        Date validity = new Date(now.getTime() + refreshTokenExpire * MILLI_SECOND);

        return Jwts.builder()
                .signWith((new SecretKeySpec(secretKey.getBytes(), SignatureAlgorithm.HS512.getJcaName()))) //HS512 알고리즘 이용, secretKey 이용
                .setIssuer(issuer)
                .setIssuedAt(now)
                .setExpiration(validity)
                .compact();
    }

    public String reissueAccessToken(String refreshToken, ReissueTokenReqDTO reissueTokenReqDTO){
        // refreshToken 확인
        validateToken(refreshToken);
        String accessToken = reissueTokenReqDTO.getAccessToken();
        String redisRefreshToken = redisService.getRefreshToken(accessToken);

        // 기존 refreshToken 일치 확인
        if (!Objects.equals(redisRefreshToken, refreshToken)){
            throw new TokenException(ErrorCode.TOKEN_INVALID);
        }

        // newAccessToken 생성
        Long id = Long.valueOf(extractSubFromToken(accessToken));
        String newAccessToken = createAccessToken(id);

        // redis 업데이트
        redisService.updateRefreshTokenKey(accessToken, newAccessToken);

        return newAccessToken;
    }

    public String getSubject(String token){
        return Jwts.parserBuilder()
                .setSigningKey(secretKey.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public void validateToken(String token){
        try{
            Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                    .build()
                    .parseClaimsJws(token);
        }catch(ExpiredJwtException e){
            throw new TokenException(ErrorCode.TOKEN_EXPIRED);
        }catch(JwtException | IllegalArgumentException | NullPointerException e){
            throw new TokenException(ErrorCode.TOKEN_INVALID);
        }
    }

    public int getRefreshTokenExpire() {
        return (int) refreshTokenExpire;
    }

    public String extractSubFromToken(String jwtToken) {
        // JWT 토큰을 "."으로 나눠서 header, payload, signature로 분리
        String[] parts = jwtToken.split("\\.");

        // payload 부분을 Base64 디코딩하여 JSON 문자열을 얻음
        String payloadBase64 = parts[1];
        String payloadJson = new String(Base64.getUrlDecoder().decode(payloadBase64), StandardCharsets.UTF_8);

        // JSON 문자열을 파싱하여 sub 값을 추출
        return payloadJson.substring(8, 13);
    }
}
