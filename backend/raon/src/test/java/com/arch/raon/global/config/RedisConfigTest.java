package com.arch.raon.global.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class RedisConfigTest {

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    @Test
    public void testStrings() throws Exception {

        //given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "accessToken";

        //when
        valueOperations.set(key, "test");

        //then
        String value = valueOperations.get(key);
        assertThat(value).isEqualTo("test");
    }

}