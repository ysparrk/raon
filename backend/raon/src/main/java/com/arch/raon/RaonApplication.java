package com.arch.raon;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
@EnableCaching
@SpringBootApplication
@RequiredArgsConstructor
public class RaonApplication {

	private final RedisTemplate redisTemplate;

	@PostConstruct
	public void setRedisTemplate() {
		redisTemplate.getConnectionFactory().getConnection().flushAll();
	}

	public static void main(String[] args) {
		SpringApplication.run(RaonApplication.class, args);
	}

}
