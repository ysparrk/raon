package com.arch.raon.global.auth.service;

import com.arch.raon.global.auth.exception.TokenException;
import com.arch.raon.global.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.util.Date;

@Service
public class JwtService {
    private static final long MILLI_SECOND = 1000L;
    private final String secretKey;

    private final long expirationHours = 60 * 30;
    private final String issuer;

    //30일
    private final long refreshTokenExpire = 60 * 60 * 24 * 30;

    public JwtService(
            @Value("${custom.jwt.issuer}") String issuer,
            @Value("${custom.jwt.secretKey}") String secretKey
    ) {
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

}
