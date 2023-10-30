package com.arch.raon.global.auth.config;

public class ExpireTime {
    public static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;               // 30분    // 2주
    public static final long REFRESH_TOKEN_EXPIRE_TIME = 2 * 7 * 24 * 60 * 60 * 1000L;     // 2주
}
